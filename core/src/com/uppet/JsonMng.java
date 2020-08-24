package com.uppet;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonMng {
    private static GameInfo gameInfo;
    static {
        gameInfo = GameInfo.getInstance();

    }
    public static void saveGameInfoInternal(){
        FileWriter file = null;
        JSONObject obj = new JSONObject();
        JSONObject info = new JSONObject();
        info.put("Name","");
        info.put("Difficult",gameInfo.getLevel());
        info.put("High score",gameInfo.getHighScore());
        info.put("AudioSetting",gameInfo.getAudioSetting());
        obj.put("GameInfo",info);

        try {
            file = new FileWriter("fileSave/saveFile.txt");
            file.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readGameInfoInternal(){
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("fileSave/savefile.txt"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);


            parseGameInfoInternal((JSONObject) obj);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseGameInfoInternal(JSONObject gameInfoJSon)
    {
        JSONObject gameInfoObject = (JSONObject) gameInfoJSon.get("GameInfo");

        if(gameInfoObject != null) {
            Long level = (long) gameInfoObject.get("Difficult");
            gameInfo.setLevel(level.intValue());

            Long hightScore = (long) gameInfoObject.get("High score");
            gameInfo.setHighScore(hightScore.intValue());

            //Get employee website name
            String name = (String) gameInfoObject.get("Name");

            String audio = (String) gameInfoObject.get("AudioSetting");
            if(audio != null) {
                if (audio.equals("on")) {
                    gameInfo.setMusic(true);
                }
                else gameInfo.setMusic(false);
            }
        }
    }

}
