import java.util.ArrayList;
import java.util.Iterator;

public class InfoMod extends Module
{
   InfoMod()
   {
      super("SKKI");
      moduleName = "InfoMod";
   }

   public void onMessage(String channel, String sender,
      String login, String hostname, String message)
   {
      if(OpHelp.command(message, "truepurpose"))
      {
         bot.sendNotice(channel, "Created so that senpai can notice me.");
      }
      else if(OpHelp.command(message, "about"))
      {
         bot.sendNotice(channel, "Created for different IRC functions"
            + " such as leaving messages when people are offline. "
            + "A powerful back system for wide range of functions. "
            + "Currently in Alpha.");
      }
      else
      {
         bot.sendNotice(sender, getDisplayModules());
      }
   }

   public void onPrivateMessage(String sender, String login, 
      String hostname, String message)
   {
      onMessage(sender, sender, login, hostname, message);
   }

   public void onJoin(String channel,String sender, 
      String login,String hostname)
   {
      return ;
   }

   public String getDisplayModules()
   {
      String[] str = getModuleNames();
      String display = "Loaded Modules: ";
      if(str.length != 0)
      {
         display += str[0];
      }
      for(int x = 1; x < str.length; ++x)
      {
         display += "," + str[x];
      }
      return display;
   }

   public String[] getModuleNames()
   {
      ArrayList<Module> modules = bot.getModules();
      String[] names = new String[modules.size()];
      Iterator<Module> it = modules.iterator();
      Module cMod;
      for(int x = 0; it.hasNext(); ++x)
      {
         cMod = it.next();
         if(cMod.getTrigger().equals(""))
         {
            names[x] = cMod.getName();
         }
         else
         {
            names[x] = cMod.getName() + " (" + cMod.getTrigger() + ")";
         }
      }
      return names;
   }

}
