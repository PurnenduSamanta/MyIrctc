package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;


public class TrainRoutes
{
    @FXML
    Button proceed;
    @FXML
    TextField tn;
    @FXML
    Label result,ft;
    StringBuffer stringBuffer=new StringBuffer();
    int s;


    public void  ValidateTrainSearch()
    {
       result.setText("");
        if(!(tn.getText().equals(""))) {
            if (tn.getText().matches("[0-9]+")&& tn.getText().length()<=10)
            {
                s= Integer.parseInt(tn.getText());
                API api = new API();
                String sb = api.fetchDataFromUrl("https://indian" +
                        "railapi.com/api/v2/TrainSchedule/apikey/30c382602bfa67c8a7c580e6cfe2becb/TrainNumber/" + s + "/");
                if (sb != null) {
                    JSONObject parentObject = new JSONObject(sb);
                    JSONArray jsonArray;
                    jsonArray = parentObject.getJSONArray("Route");
                    if (!(jsonArray.isEmpty())) {
                        JSONObject child;
                        stringBuffer.append("ArrivalTime" + "     " + "DepertureTime" + "      " + "StationName");
                        stringBuffer.append(System.lineSeparator());
                        stringBuffer.append("-------------------------------------------------------------------");
                        stringBuffer.append(System.lineSeparator());
                        for (int i = 0; i < jsonArray.length(); i++) {
                             child = jsonArray.getJSONObject(i);
                            stringBuffer.append(child.get("ArrivalTime") + "          " + child.get("DepartureTime") + "                 " + child.getString("StationName"));
                            stringBuffer.append(System.lineSeparator());
                        }
                        child= jsonArray.getJSONObject(0);
                        String s1=child.getString("StationName");
                        child= jsonArray.getJSONObject((jsonArray.length()-1));
                        String s2=child.getString("StationName");
                        ft.setText("From"+" "+s1+" "+"to"+" "+s2);
                        result.setText(String.valueOf(stringBuffer));
                        stringBuffer.delete(0,  stringBuffer.length());
                    } else {
                        result.setText("No trains found");
                    }
                }
                    else {
                    result.setText("Check Your Internet Connection");
                }
            }
            else
            {
                result.setText("Not valid train no");
            }
        }
        else
        {
            result.setText("Enter your train no");
        }
    }

}
