package fr.gael.dhus.transformation;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.dhus.transformation.MonitorableTask;
import org.dhus.transformation.ProductInfo;
import org.dhus.transformation.TransformationException;
import org.dhus.transformation.TransformationParameter;
import org.dhus.transformation.Transformer;

public class YesTransformer implements Transformer
{
   @Override
   public String getName()
   {
      return "Yes";
   }

   @Override
   public String getDescription()
   {
      return "Yes";
   }

   @Override
   public List<TransformationParameter> getParameters()
   {
      return Collections.emptyList();
   }

   @Override
   public MonitorableTask<URL> getTask(ProductInfo productInfo, Map<String, Object> parameters)
         throws TransformationException
   {
      return new YesMonitorableTask();
   }

}
