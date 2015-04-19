package ircmodbot;
import org.jibble.pircbot.*;

public abstract class Module 
{
   protected String trigger;
   protected String moduleName;

   protected ModBot bot;

   protected Module()
   {
      this(null, null);
   }

   protected Module(String triggerWord)
   {
      this(null, triggerWord);
   }
   
   protected Module(ModBot bot, String moduleName, String triggerWord)
   {
      if(!setBot(bot))
      {
         bot = null;
      }

      if(!setTrigger(triggerWord))
      {
         triggerWord = "ERR" + Math.random() * 10;
      }
      moduleName = "Unknownmod" + Math.random();
   }

   protected Module(ModBot bot, String triggerWord)
   {
      this(bot, null, triggerWord);
   }

   abstract public void onMessage(String channel, String sender,
      String login, String hostname, String message);

   abstract public void onJoin(String channel,String sender, 
         String login,String hostname);
   abstract public void onPrivateMessage(String sender, String login, 
      String hostname, String message);

   public boolean setBot(ModBot bot)
   {
      if(bot == null)
      {
         return false;
      }

      this.bot = bot;
      return true;
   }

   public boolean setTrigger(String triggerWord)
   {
      if(triggerWord == null)
      {
         return false;
      }

      trigger = triggerWord;
      return true;
   }

   public String getTrigger()
   {
      return trigger;
   }

   public String getName()
   {
      return moduleName;
   }

   public void wait(int sec) 
   {
      try 
      {
         Thread.sleep(1000*sec);
      } catch(InterruptedException ex) 
      {
         return ;
      }
   }
}
