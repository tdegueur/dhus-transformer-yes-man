package fr.gael.dhus.transformation;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.dhus.api.transformation.MonitorableTask;

public class YesMonitorableTask implements MonitorableTask<URL>
{
   private static enum Status {
      STARTED,
      PAUSED,
      STOPPED,
      COMPLETED
   }

   private Status status = Status.STOPPED;
   private int progression = 0;

   @Override
   public URL call() throws Exception
   {
      status = Status.STARTED;
      progression = 0;
      Thread.sleep(10_000);

      Path yesPath = Paths.get("/tmp/dhus/S3B_SR_1_SRA_BS_20181123T034407_20181123T034411_20181218T182902_0003_018_047______LN3_O_NT_003.zip");
      Files.createDirectories(yesPath.getParent());

      if(!Files.exists(yesPath))
      {
         Files.createFile(yesPath);
      }

      progression = 50;
      Thread.sleep(5_000);

      InputStream yesStream = YesMonitorableTask.class.getResourceAsStream(
            "/S3B_SR_1_SRA_BS_20181123T034407_20181123T034411_20181218T182902_0003_018_047______LN3_O_NT_003.zip");

      Files.copy(yesStream, yesPath, StandardCopyOption.REPLACE_EXISTING);

      status = Status.COMPLETED;
      return yesPath.toUri().toURL();
   }

   @Override
   public int getProgression()
   {
      return progression;
   }

   @Override
   public boolean isStarted()
   {
      return status.equals(Status.STARTED);
   }

   @Override
   public boolean isPaused()
   {
      return status.equals(Status.PAUSED);
   }

   @Override
   public boolean isStopped()
   {
      return status.equals(Status.STOPPED);
   }

   @Override
   public boolean isCompleted()
   {
      return status.equals(Status.COMPLETED);
   }
}