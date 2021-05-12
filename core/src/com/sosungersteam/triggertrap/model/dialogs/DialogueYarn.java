package com.sosungersteam.triggertrap.model.dialogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.kyper.yarn.Dialogue;
import com.kyper.yarn.DialogueData;
import com.kyper.yarn.Dialogue.LineResult;
import com.kyper.yarn.Dialogue.OptionResult;
import com.kyper.yarn.Dialogue.CommandResult;
import com.kyper.yarn.Dialogue.NodeCompleteResult;
import com.sosungersteam.triggertrap.TriggerTrap;
import com.sosungersteam.triggertrap.view.Renderer;
import com.sosungersteam.triggertrap.view.screens.MenuScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entry;
import com.badlogic.gdx.utils.StringBuilder;
import com.kyper.yarn.Dialogue;
import com.kyper.yarn.Dialogue.CommandResult;
import com.kyper.yarn.Dialogue.LineResult;
import com.kyper.yarn.Dialogue.NodeCompleteResult;
import com.kyper.yarn.Dialogue.OptionResult;
import com.kyper.yarn.Library.Function;
import com.kyper.yarn.DialogueData;
import com.kyper.yarn.Value;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DialogueYarn {
    public Array<Message> messages = new Array<>();
    public int targetMessageIndex;
    public Message targetMessage;

    SpriteBatch batch;
    //какие кнопки проверять при выборе какой-то опции в диалоге
    int[] OP_KEYS = {Input.Keys.NUM_1, Input.Keys.NUM_2, Input.Keys.NUM_3, Input.Keys.NUM_4, Input.Keys.NUM_5};
    public Path testDialogue = Paths.get("C:\\Users\\usp20\\Desktop\\IT Academy\\Android\\TriggerTrap\\android\\assets\\data\\awdsad.json");
    boolean show_tokens = false; // токены выдаваемые парсером
    boolean show_parse_tree=false; // дерево созданное парсером из списка токенов
    String only_consider = null; // если null - загружает весь диалог
    DialogueData data = new DialogueData("Test_data");
    Dialogue test_dialogue; // сам класс диалога
    //виды результатов
    //Line-текстовые линии диалога, Option- набор выборов, где нужно сделать свой choice ,
    // Command- персональные команды, которые могут быть парснуты прогером , NodeComplete - сообщает, что данный узел завершен (не всегда означает конец исполнения).
    LineResult current_line = null;
    OptionResult current_options = null;
    CommandResult current_command = null;
    NodeCompleteResult node_complete = null;
    //Шрифт и бетч
    BitmapFont font;
    //StringBuilder создается для избежания создания строк
    StringBuilder option_string;
    // Инициализация строк наиболее используемых
    final String TAB ="    ";
    final String ps = "PRESS ACT BUTTON TO CONTINUE";
    final String finished = "You finished!";
    final String vars = "Variables: ";
    final String see_ship_var ="$should_see_ship";
    final String sally_warning_var="$ally_warning";
    final String talk_to_ship = "Talk to Ship";
    final String talk_to_sally="Talk to Sally";
    //проверка на завершенность всего диалога, т.е. все узлы закончены.
    boolean complete = true;
    boolean byte_code_printed = false; // должен сбросить байт-код после завершения
    //Добавление своих текстур...
    ObjectMap<String,Texture> ship_textures;
    ObjectMap<String,Texture> sally_textures;
    Texture ship_face;
    Texture sally;
    int screenWidth;
    int screenHeight;
    final String poop="$poop";
    public void create() throws IOException {
        System.out.println(testDialogue.getFileName());
        //загрузка объявленных текстур...
//        ship_textures.put("happy", new Texture(Gdx.files.internal("happy.png")));
//		ship_textures.put("neutral", new Texture(Gdx.files.internal("neutral.png")));
//
//		ship_face = ship_textures.get("neutral");
//
//		sally_textures = new ObjectMap<String, Texture>();
//		sally_textures.put("default", new Texture(Gdx.files.internal("sally.png")));
//		sally_textures.put("angry", new Texture(Gdx.files.internal("sally_angry.png")));
//		sally_textures.put("talk", new Texture(Gdx.files.internal("sally_talk.png")));
//
//		sally = sally_textures.get("default");
//
//		// enter some variables for our dialogue to use -
//		// we do not need to do this but just to access them before the dialogue
//		// we do for testing purposes
		data.put(see_ship_var, false);
		data.put(sally_warning_var, false);
	    data.put(poop, "empty");
        option_string=new StringBuilder(400);
        batch = new SpriteBatch();
        font = MenuScreen.createFont(16,1, Color.WHITE,0,0,Color.WHITE);
        test_dialogue=new Dialogue(data);
        // we will register a custom function to the library that takes in
//		// one parameter - sally's action sprite
//		test_dialogue.getLibrary().registerFunction("setSallyAction", 1, new Function() {
//			@Override
//			public void invoke(Value... params) {
//				// this function only has one parameter so check like so
//				Value action = params[0];// this parameter will be the name of the action sprite to set sally to
//
//				// possible actions include
//				// angry
//				// talk
//				// and default
//
//				// see if the sprite exists
//				if (sally_textures.containsKey(action.getStringValue())) {
//					sally = sally_textures.get(action.getStringValue());// set it
//				} else {
//					// otherwise set it to default
//					sally = sally_textures.get("default");
//				}
//
//			}
//		});
        test_dialogue.loadFile(testDialogue,show_tokens,show_parse_tree,only_consider);
        test_dialogue.start("Start");
    }
    public void render() {
        if (screenWidth == 0) {
            screenWidth = 250;
            screenHeight = 150;
        }






        //UPDATE---
        // если у нас нет никаких команд и следующий результат команда
        if (current_command == null && test_dialogue.isNextCommand()) {
            System.out.println("asdasd");
            current_command = test_dialogue.getNextAsCommand();
        }
        // если у нас нет линии 0 проверяем является ли следующий результат линией
        else if (current_line == null && test_dialogue.isNextLine()) {
            //Если здесь команда перед линией, то выполняем её
            //executeCommand();
            System.out.println("Next line");
            current_line = test_dialogue.getNextAsLine();
        }
        //если у нас нет опций и следующий результат опция
        else if (current_options == null && test_dialogue.isNextOptions()) {
            current_options = test_dialogue.getNextAsOptions();
            System.out.println("+option: "+current_options);
        }
        //если узел не нашёл полное завершение - проверяем является ли следующим полным заключением
        else if (node_complete == null && test_dialogue.isNextComplete()) {
            System.out.println("Next complete");
            node_complete = test_dialogue.getNextAsComplete();
        } else {
            //ждём линии или нет доступных результатов
            //проверить обработана ли строка (null) и что у нас есть узел
            //полное завершение
            //выполняем оставшиеся команды
            if (current_line==null && node_complete!=null){
            //executeCommand();
            complete = true;
            //обнуляем все результаты
            resetAllResults();
            test_dialogue.stop();
        }}




        updateInput();


        //UPDATE END ------
        //DRAW --------

      batch.begin();

        if (!complete){
            //draw dialogue
            if (current_line!=null) {
                font.draw(batch, current_line.getText(), screenWidth * .1f, screenHeight * .8f);
                if (current_options == null) // maybe != ????
                    font.draw(batch, ps, screenWidth * .3f, screenHeight * .1f);

            }

            /*
            if (current_options!=null){
                int check_limit = Math.min(current_options.getOptions().size(),OP_KEYS.length);
                for (int i=0;i<check_limit;i++){
                    String option = current_options.getOptions().get(i);
                    option_string.setLength(0);
                    option_string.append('[').append(i+1).append(']').append(':').append(' ').append(option);
                    font.draw(batch,option_string,screenWidth*.3f,(screenHeight * .5f) - (20 * i));
                }
            }*/
        }
        /*
            else{
                option_string.setLength(0);
                option_string.append('[').append(1).append(']').append(':').append(" Talk to ship");
                option_string.append(TAB).append(TAB).append(TAB).append(TAB).append(TAB).append(TAB).append(TAB).append(TAB);
                option_string.append('[').append(2).append(']').append(':').append(" Talk to Sally");
                font.draw(batch, option_string, screenWidth * .23f, screenHeight * .5f);
            }*/

            // debug lines
            //option_string.setLength(0);
           // option_string.appendLine(vars);
           // option_string.append(see_ship_var).append('=').append(data.getBoolean(see_ship_var)).append('\n');
           // option_string.append(sally_warning_var).append('=').append(data.getBoolean(sally_warning_var)).append('\n');
           // option_string.append(poop).append('=').append(data.getString(poop));
           // font.draw(batch,option_string,screenWidth*.3f,screenHeight);
            //		// draw sprites
//		if (sally != null)
//			batch.draw(sally, screenwidth - sally.getWidth() * 2f, screenheight * .4f);
//
//		if (ship_face != null)
//			batch.draw(ship_face, ship_face.getWidth(), screenheight * .4f);
//
            batch.end();
            //END DRAW;
        }
        final String setsprite_command="setsprite";
        final String shipface_identifier="ShipFace";
        /*
        private void executeCommand(){
            if (current_command!=null){
                String params[]=current_command.getCommand().split("\\s+");
                for (int i=0;i<params.length;i++){
                    params[i]=params[i].trim();
                }
                if (params[0].equals(setsprite_command)){
                    if(params.length==3){
                        if(params[1].equals(shipface_identifier)){
                            if(ship_textures.containsKey(params[2])){
                                ship_face=ship.textures.get(params[2]);
                            }
                            else{
                                ship_face=ship_textures.get("neutral");
                            }
                        }
                    }
                }
                current_command=null;
            }
    }*/


    //used to update the input so we can progress the dialogue
    public void updateInput(){
            if (Gdx.input.isKeyJustPressed(Keys.D)){
                System.out.println(data.toJson());
            }
            if (complete) {
                /*
                if (Gdx.input.isKeyJustPressed(OP_KEYS[3])) {
                    //talk to ship
                    test_dialogue.start();
                    complete = false;
                    resetAllResults();
                }

                if (Gdx.input.isKeyJustPressed(OP_KEYS[4])) {
                    test_dialogue.start("Sally");
                    complete = false;
                    resetAllResults();
                }
                */
                if (Gdx.input.isTouched()) {
                    test_dialogue.start("Start");
                    complete=false;
                    resetAllResults();
                }
                return;
            }
            if (current_line!=null && current_options==null){
                if(Gdx.input.isTouched()){
                    clearLine();
                }
            }
            if (current_options!=null){
                int check_limit = Math.min(current_options.getOptions().size(), OP_KEYS.length);
                System.out.println("Option chosen");
                System.out.println(check_limit);
                for (int i=0;i<check_limit;i++){

                    if (Gdx.input.isKeyJustPressed(OP_KEYS[i])){
                        System.out.println("button pressed");
                        current_options.choose(i);
                        current_options=null;
                        clearLine();
                        break;
                    }
                }
            }
    }
    private void clearLine(){
            current_line=null;
    }
    private void resetAllResults(){
            current_line=null;
            current_options=null;
            node_complete=null;
            current_command=null;
    }
    public void dispose(){
            //batch.dispose();
            font.dispose();
            for (Entry<String,Texture>t:ship_textures){
                t.value.dispose();
            }
            for (Entry<String,Texture>t:sally_textures)
                t.value.dispose();
    }
}
