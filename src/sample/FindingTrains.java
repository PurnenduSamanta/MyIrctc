package sample;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.json.JSONArray;
import org.json.JSONObject;
public class FindingTrains {
    @FXML
    javafx.scene.control.TextField ss, ds;
    @FXML
    javafx.scene.control.Button proceed;
    @FXML
    Label result;
    StringBuffer stringBuffer = new StringBuffer();


    public void FindTrains() {
        result.setText("");
        if (!(ss.getText().equals("")) && (!(ds.getText().equals("")))) {
            if ((ss.getText().matches("^[a-zA-Z]*$"))&& (ds.getText().matches("^[a-zA-Z]*$")) && (ss.getText().length()<=7) && (ds.getText().length()<=7)) {
                String p = ss.getText();
                String q = ds.getText();
                API api = new API();
                String sb = api.fetchDataFromUrl("https://indianrailapi.com/api/v2/TrainBetweenStation/apikey/30c382602bfa67c8a7c580e6cfe2becb/From/" + p + "/To/" + q);
                if (sb != null) {
                    JSONObject jsonObject = new JSONObject(sb);
                    if(((jsonObject.getInt("ResponseCode"))==201)&&(jsonObject.getString("Status").equals("FAILED")))
                    {
                        result.setText("No train found");
                    }
                    else {
                        JSONArray jsonArray = jsonObject.getJSONArray("Trains");
                        if (jsonArray != null)
                        {
                            stringBuffer.append("Total trains found" + jsonObject.get("TotalTrains"));
                            stringBuffer.append(System.lineSeparator());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                stringBuffer.append(jsonObject1.get("TrainNo") + "    " + jsonObject1.get("TrainName"));
                            }
                            result.setText(String.valueOf(stringBuffer));
                            stringBuffer.delete(0,stringBuffer.length());
                        } else {
                            result.setText("No train found");
                        }
                    }
                } else {
                    result.setText("Check Your Internet Connection");
                }
            }
            else
            {
                result.setText("Enter valid station code");
            }
        } else {
            result.setText("Enter  station code");
        }
    }
}
