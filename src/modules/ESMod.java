package modules;
import ircmodbot.Module;
import ircmodbot.OpHelp;


public class ESMod extends Module
{
   private String regenPass;
   private String randPass;

   public ESMod()
   {
      super("ESM");
      moduleName = "Exit System Mod";
      randPass = Math.random()*100 + "";
      regenPass = "givemekey";
   }

   public void onMessage(String channel, String sender,
      String login, String hostname, String message)
   {
      if(OpHelp.command(message,"exit").length() != 0)
      {
         message = OpHelp.subString(message ,5, message.length());
         if(message.equalsIgnoreCase(randPass))
         {
            bot.sendMessage(channel, "Shutting down.");
            System.exit(0);
         }
      }
   }

   public void onPrivateMessage(String sender, String login, 
      String hostname, String message)
   {
      message = OpHelp.command(message, "pregen");
      if(message.length() != 0)
      {
         System.out.println(message);
         if(OpHelp.command(message, regenPass).length() != 0)
         {
            regenPassword();
            bot.sendMessage(sender, randPass);
         }
      }
   }

   public void onJoin(String channel,String sender, 
      String login,String hostname)
   {
      return ;
   }

   public void regenPassword()
   {
      randPass = Math.random()*100+"";
   }
}