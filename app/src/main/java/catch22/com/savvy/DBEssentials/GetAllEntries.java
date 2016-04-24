package catch22.com.savvy.DBEssentials;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.sql.SQLException;

import catch22.com.savvy.SettingsActivity;
import catch22.com.savvy.SplashScreen;

/**
 * Created by root on 24/4/16.
 */
public class GetAllEntries{
    private static LoginDataBaseAdapter m;

    public static void insertData(Context ctxt) {
        m = new LoginDataBaseAdapter(ctxt);

        try {
            m = m.open_hack();
        }catch(SQLException e){
            e.printStackTrace();
        }
        if (!m.exists()) {
            ///whatsapp
            m.insertEntry("whatsapp", "make", "group", 1);
            m.insertEntry("whatsapp", "change", "number", 2);
            m.insertEntry("whatsapp", "create", "contact", 3);
            m.insertEntry("whatsapp", "make", "contact", 3);
            m.insertEntry("whatsapp", "add", "contact", 3);
            m.insertEntry("whatsapp", "view", "contact",4);
            m.insertEntry("whatsapp", "change", "dp",5);
            m.insertEntry("whatsapp", "modify", "dp",5);
            m.insertEntry("whatsapp", "update", "dp",5);
            m.insertEntry("whatsapp", "save", "dp",6);
            m.insertEntry("whatsapp","view","dp",6);
            m.insertEntry("whatsapp", "delete", "account",7);
            m.insertEntry("whatsapp", "create", "group",8);
            m.insertEntry("whatsapp", "make", "group",8);
            m.insertEntry("whatsapp", "make", "admin group",47);//
            m.insertEntry("whatsapp", "mute", "group",9);
            m.insertEntry("whatsapp", "exit", "group",10);
            m.insertEntry("whatsapp", "leave", "group",10);
            m.insertEntry("whatsapp", "invite", "friend",11);
            m.insertEntry("whatsapp", "change", "name",12);
            m.insertEntry("whatsapp", "update", "name",12);
            m.insertEntry("whatsapp", "modify", "name",12);
            m.insertEntry("whatsapp", "change", "notification tone",13);
            m.insertEntry("whatsapp", "update", "notification tone",13);
            m.insertEntry("whatsapp", "modify", "notification tone",13);
            m.insertEntry("whatsapp", "change", "message tone",14);
            m.insertEntry("whatsapp", "update", "message tone",14);
            m.insertEntry("whatsapp", "modify", "message tone",14);
            m.insertEntry("whatsapp", "change", "tone",15);
            m.insertEntry("whatsapp", "update", "tone",15);
            m.insertEntry("whatsapp", "modify", "tone",15);
            m.insertEntry("whatsapp", "create", "message starred",16);
            m.insertEntry("whatsapp", "make", "message starred",16);
            m.insertEntry("whatsapp", "star", "message",16);
            m.insertEntry("whatsapp", "view", "list message star",17);
            m.insertEntry("whatsapp", "see", "list message star",17);
            m.insertEntry("whatsapp", "unstar", "message",18);
            m.insertEntry("whatsapp", "remove", "message starred",18);
            m.insertEntry("whatsapp", "change", "status",19);
            m.insertEntry("whatsapp", "update", "status",19);
            m.insertEntry("whatsapp", "modify", "status",19);
            m.insertEntry("whatsapp", "use", "web whatsapp",20);
            m.insertEntry("whatsapp", "setup", "web whatsapp",20);
            m.insertEntry("whatsapp","attach","photo",38);
            m.insertEntry("whatsapp","attach","video",38);
            m.insertEntry("whatsapp","attach","multiple photos",38);
            m.insertEntry("whatsapp","send","photo",38);
            m.insertEntry("whatsapp","send","video",38);
            m.insertEntry("whatsapp","send","number",38);
            m.insertEntry("whatsapp","send","contact",38);
            m.insertEntry("whatsapp","send","document",38);
            m.insertEntry("whatsapp","attach","document",38);
            m.insertEntry("whatsapp","attach","contact",38);
            m.insertEntry("whatsapp","change","wallpaper",39);
            m.insertEntry("whatsapp","update","wallpaper",39);
            m.insertEntry("whatsapp","delete","message",40);
            ////dialer
            m.insertEntry("dialer","create","contact",21);
            m.insertEntry("dialer","make","contact",21);
            m.insertEntry("dialer","remove","dial speed",22);
            m.insertEntry("dialer","remove","speeddial",22);
            m.insertEntry("dialer","open","call history",23);
            m.insertEntry("dialer","see","call history",23);
            m.insertEntry("dialer","call","",24);
            m.insertEntry("dialer","call","",24);
            m.insertEntry("dialer","dial","number",25);
            m.insertEntry("dialer","call","number",25);
            m.insertEntry("dialer","import","sim",26);
            m.insertEntry("dialer","import","contact",26);
            m.insertEntry("dialer","import","contacts",26);
            m.insertEntry("dialer","change","ringtone",27);
            m.insertEntry("dialer","sort","contacts",28);
            m.insertEntry("dialer","change","format name",29);
            m.insertEntry("dialer","select","format name",29);
            m.insertEntry("dialer","enable","call vibration",30);
            m.insertEntry("dialer","disable","call vibration",30);
            m.insertEntry("dialer","check","balance",31);
            ////messaging
            m.insertEntry("messaging","create","message",32);
            m.insertEntry("messaging","write","message",32);
            m.insertEntry("messaging","send", "message",32);
            m.insertEntry("messaging","send","sms",32); 
            m.insertEntry("messaging","search","conversation",33);
            m.insertEntry("messaging","search","chat",33);
            m.insertEntry("messaging","search","message new",34);
            m.insertEntry("messaging","write","number",35);
            m.insertEntry("messaging","text","number",35);
            m.insertEntry("messaging","send","number",35);
            m.insertEntry("messaging","send","smiley",36);
            m.insertEntry("messaging","send","emoticon",36);
            m.insertEntry("messaging","send","face sad",36);
            m.insertEntry("messaging","attach","picture",37);
            m.insertEntry("messaging","attach","number",37);
            m.insertEntry("messaging","attach","contact",37);
            m.insertEntry("messaging","attach","video",37);
            m.insertEntry("messaging","attach","audio",37);
            m.insertEntry("messaging","enable","delivery report",41);
            m.insertEntry("messaging","select","delivery report",41);
            m.insertEntry("messaging","get","delivery report",41);
            m.insertEntry("messaging","disable","delivery report",41);
            m.insertEntry("messaging","deselect","delivery report",41);
            m.insertEntry("messaging","change","notification tone",42);
            m.insertEntry("messaging","modify","notification tone",42);
            m.insertEntry("messaging","update","notification tone",42);
            
            m.insertEntry("launcher","change","wallpaper",43);
            m.insertEntry("launcher","modify","wallpaper",43);
            m.insertEntry("launcher", "set","wallpaper",43);
            m.insertEntry("launcher","change","brightness",44);
            m.insertEntry("launcher","lower","brightness",44);
            m.insertEntry("launcher","decrease","brightness",44);
            m.insertEntry("launcher","increase","brightness",44);
            m.insertEntry("launcher","start","data",45);
            m.insertEntry("launcher","stop","data",45);
            m.insertEntry("launcher","switch","data",45);
            m.insertEntry("launcher","toggle","data",45);
            m.insertEntry("launcher","start","wifi",45);
            m.insertEntry("launcher","stop","wifi",45);
            m.insertEntry("launcher","switch","wifi",45);
            m.insertEntry("launcher","toggle","wifi",45);
            m.insertEntry("launcher","start","internet",45);
            m.insertEntry("launcher","stop","internet",45);
            m.insertEntry("launcher","switch","internet",45);
            m.insertEntry("launcher","toggle","internet",45);
            m.insertEntry("launcher","toggle","bluetooth",46);
            m.insertEntry("launcher","switch","bluetooth",46);
            m.insertEntry("launcher","start","bluetooth",46);
            m.insertEntry("launcher","stop","bluetooth",46);

        }

    }

    public static String getSolution(int x)
    {
        String solution;
        switch(x)
        {
            case 1:
                solution = "Open WhatsApp and go to the Chats screen.\n" +
                        "At the top of the Chats screen, tap the 3-dot button at the upper right corner.\n" +
                        "Type in a subject or title. ...\n" +
                        "Add group participants by selecting (+), or by typing the name of the contact.\n" +
                        "Tap Create to finish creating the group.";
                break;
            case 2:
                solution = "Tap the 3-dot button at the upper right corner.\n" +
                        "Go to Settings > Account > Change Number.\n";
                 break;
            case 3:
                solution =  "Press the new conversation button at the top right corner beside the 3-dot button\n" +
                        "Select a contact by scrolling through the list\n" ;
                break;
            case 4:
                solution = "Go to the chat screen(main screen).\n" +
                        "Swipe right.\n";
                break;
            case 5:
                solution = "Tap the 3-dot button at the upper right corner.\n" +
                        "Go to Settings > Profile \n" +
                        "Click on the Edit icon (Pencil)";
                break;
            case 6:
                solution = "To view DP go to Settings > Profile\n"+
                        "To make further changes click on the edit button\n";
                break;
            case 7:
            solution = "To delete your account go to Settings > Account > Delete my Account\n";
                break;
            case 8:
                solution = "To create a group click on 3-dot menu > New Group\n";
                break;

            case 9:
                solution = "To mute the group click on the group desired to be muted\n"+
                        "Click on the 3-dot menu > mute\n";
                break;
            case 10:
                solution = "Long click on the group you want to exit > Exit group\n";
                break;
            case 11:
                solution = "Go to the contacts tab > Scroll down to the bottom\n"+
                        "Select the person whom you want to send an invite\n";
                break;
            case 12:
                solution = "To change your Whatsapp name go to Settings > Profile\n"+
                        "Click on the edit button beside you name\n";
                break;
            case 13:
                solution = "To modify notification tone go to Settings > Notification > Notification tone\n";
                break;
            case 14:
                solution = "To change the Message tone go to 3-dot menu > Settings\n"+
                        "In the Notifications > Sound\n";
                break;
            case 15:
                solution = "To modify notification tone go to Settings > Notification \n"+
                        "Go to Call notifications > Ringtone\n";
                break;
            case 16:
                solution = "Go to a chat and press and hold the particular text you want to star.\nPress the starred icon at the top\n";
                break;
            case 17:
                solution = "To view all the starred messages, press the 3-dot button at the top right corner\nGo to 'Starred messages'\n";
                break;
            case 18:
                solution = "Go to the list of starred messages by pressing the 3-dot button at the top right corner\nPress and hold the message you want to remove frm the list\nPress the crossed star button\n";
                break;
            case 19:
                solution ="Press the 3-dot button at the top right corner\nGo to 'status'\nPress the pen button beside your current status and update it\n";
                break;
            case 20:
                solution = "Press the 3-dot button at the top right corner\nGo to 'WhatsApp Web'\n";
                break;
            case 21:
                solution = "To create a new contact, tap the 3-dot button at the top right corner, beside the mic button\n Go to 'New Conatact'\nFill out Name, Phone and any further data you would like to keep\n Click the back button to save the contact\n";
                break;
            case 22:
                solution ="To remove a speed dial, press and hold the icon of the contact you wish to remove\nWhile holding it, drag it to the 'remove' option below the search field\n";
                break;
            case 23:
                solution = "To view the call history, simply swipe right or press on 'Recents' option\nSwiping once more will direct you to your Contacts list\n";
                break;
            case 24:solution = "To call someone on, either press the name on the current screen\nIf the name is not present, press the 'Contacts' option and scroll through your contacts to find the person you want to call\nYou could also type the name of the person in search bar by clicking on the white space on top\nIf you wish to call a number not saved in your contacts, press the blue button with 9 dots on the bottom center of the screen\n";
                break;
            case 25:	solution="To call someone on, either press the name on the current screen\nIf the name is not present, press the 'Contacts' option and scroll through your contacts to find the person you want to call\nYou could also type the name of the person in search bar by clicking on the white space on top\nIf you wish to call a number not saved in your contacts, press the blue button with 9 dots on the bottom center of the screen\n";
                break;
            case 26:	solution="Press the 3-dot button on the top right corner in the white space\nGo to 'Import/export'\nGo to 'Import from SIM Card' and click on the Google account\n";
                break;
            case 27:	solution="Press the 3-dot button on the top right corner in the white space\nGo to Settings > General\nClick on 'Phone Ringtone'";
                break;
            case 28:	solution="Press the 3-dot button on the top right corner in the white space\nGo to Settings > General\nClick on 'Sort by'";
                break;
            case 29:	solution="Press the 3-dot button on the top right corner in the white space\nGo to Settings > General\nClick on 'Name format'";
                break;
            case 30:	solution="Press the 3-dot button on the top right corner in the white space\nGo to Settings > General\nCheck 'Also vibrate for calls'";
                break;
            case 31:	solution= "To check balance\n"+
                    "IDEA subscribers dial:*121#\n"+
                    "Vodafone subscribers dial:*111#\n"+
                    "Airtel subscribers dial:*123#\n"+
                    "Aircel subscribers dial:*125#\n"+
                    "TATA DOCOMO subscribers dial:*111#\n"+
                "Relaiance subscribers dial:*367#\n";
                break;
            case 32:	solution="Click on New Message icon (has + sign) on top right\n";
                break;
            case 33:	solution="Click on Search icon (Magnifying glass) on top right\n";
                break;
            case 34:	solution="Click on Search icon (Magnifying glass) on top right\n";
                break;
            case 35:	solution="Click on New Message icon (has + sign) on top right\n";
                break;
            case 36:	solution= "Long press the Enter key on your keyboard\nClick on the emoji of your choice\nPress send";
                break;
            case 37:	solution= "Open a chat.\nTap the paperclip at the top of the screen.\nChoose what you want to send:\nChoose Document to select a document from your phone.\nChoose Camera to take a picture with your camera.\nChoose Gallery to select an existing photo or video from your phone. You can long press to select multiple images.\nChoose Audio to record a message or send an existing audio from your phone.\nChoose Location to send your location or a nearby place.\nChoose Contact to send the information of a contact saved in your phone's address book.\nPress Send.";
                break;
            case 38:	solution= "Open a chat.\nTap the paperclip at the top of the screen.\nChoose what you want to send:\nChoose Document to select a document from your phone.\nChoose Camera to take a picture with your camera.\nChoose Gallery to select an existing photo or video from your phone. You can long press to select multiple images.\nChoose Audio to record a message or send an existing audio from your phone.\nChoose Location to send your location or a nearby place.\nChoose Contact to send the information of a contact saved in your phone's address book.\nPress Send.";
                break;
            case 39:	solution= "Open WhatsApp Messenger and tap on the Menu key.\nGo to Settings > Chat Settings.\nTap on Wallpaper.\nTap on Android System > Gallery.";
                break;
            case 40:	solution= "Open WhatsApp and go to the chat window with the message you want to delete.\nTap and hold on the message.\nOptionally, tap on more messages to select multiple messages.\nTap on the trash can icon on top of the chat screen.";
                break;
            case 41:	solution= "Select the 3 dots at the top of the screen\nTap Settings\nTap Text Messages\nCheck the box next to Delivery";

                break;
            case 42:	solution= "Select the 3 dots at the top of the screen\nTap Settings\nScroll down to the Notifications section and tap Sound.\nSelect a new notification sound from the List.";
                break;
            case 43:	solution= "Click and hold the homescreen\n"+"Choose the Wallpaper option\n";
                break;
            case 44:	solution= "Swipe from top, Click on the top right-most icon\n"+
                    "Move the slider to get the appropriate brightness\n";

                break;
            case 45:	solution= "Hold the notification bar on the top on the screen and drag it down\nTap the Wifi / Mobile Data option to enable it.";

                break;
            case 46:	solution= "Hold the notification bar on the top on the screen and drag it down\nTap the Bluetooth option to enable it.";
                break;
            default: solution = "qwertyuiopasdfghjklzxcvbnm";
        }
        return solution;



    }


}
