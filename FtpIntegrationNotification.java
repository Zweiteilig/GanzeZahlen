import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
       /* -- Handle Exceptions
       .onException(Exception.class)
         .process(errorProcessor)
         .handled(true)
        */

public class FtpIntegrationNotification
{
    public FtpIntegrationNotification()
    {

    }
   
    public static void process(org.apache.camel.Exchange exchange) throws Exception {
        /*Exception e=(Exception)exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
        e.printStackTrace();
        if(e instanceof MyException){
            MyCustomMessage myMsg=new MyCustomMessage(exchange);
            logger.error(((MyException) e).getErrorCode(),((MyException) e).getErrorDesc());
        }else{
            MyCustomMessage myMsg=new MyCustomMessage(exchange);            
            logger.error(myMsg.getFromRouteId(),e.getMessage());
        }*/
        System.out.println("\t--> process the exception...\n");
    }

   public static void main(String[] args) throws Exception 
   {       
       int ThreeStacks = 30;
       
       CamelContext context = new DefaultCamelContext();

       context.addRoutes(new RouteBuilder() 
       {
           public void configure() 
           {                
               String id = "1";
               String Protocol = "FTP".toLowerCase();
               String Username = "hwestmoreland";
               String Server = "ftp.drivehq.com";
               String Port = "21";
               String Folder = "wwwhome";
               String Password = "OneMillionU$D";
               String DeleteFiles = "N";
               String ClientDataDir = "C:/Mesh/integers/dest_files";
               String ClassName = "com.ecologic.leaseacc.api.InvokeAPIFromFile";
               String Metadata = "ImportDeals";
               String Client = "ACME";
               String FilenamePattern = "home.htm";
               String FromEndPoint = Protocol + "://" + Username + "@" + 
               Server + ((Port != null && Port.length() > 0) ? ":" + Port : "");
               FromEndPoint += ((Folder != null && Folder.length() > 0 ) ? "/" + Folder : "");
               FromEndPoint += "/";

               String fromParameters = "include=" + FilenamePattern.replaceAll("\\.", "\\.").replaceAll("\\*", ".*");
               fromParameters += ((Password != null && Password.length() > 0) ? ("&password=" + Password) : "");
               fromParameters += ("Y".equals(DeleteFiles) ? "&delete=true" : "");
                            
               if (fromParameters.length() > 0)
               {
                   FromEndPoint += "?";
                   FromEndPoint += fromParameters;
               }       
           
               String toEndPoint = "file:C:/Mesh/integers/dest_files";

                System.out.println("\t--- -- ----- -- ---");
                String urlHeads = FromEndPoint;
                String urlTails = fromParameters;
                System.out.println("\tFROM, ftop://hwestmoreland@ftp.drivehq.com/wwwhome/images/?include=logo.jpg&password=OneMillionU$D");
                System.out.println("\t---");
                System.out.println("\tTO, EndPoint: "+ toEndPoint);
                System.out.println("\t---");                
                System.out.println("\tParameters: "+ urlTails);
                System.out.println("\t---");
                System.out.println("\n");

               try
               {
                       from("ftop://hwestmoreland@ftp.drivehq.com/wwwhome/images/?include=logo.jpg&password=OneMillionU$D")
                        .to(toEndPoint);
               }
               catch (org.apache.camel.component.file.GenericFileOperationFailedException gFile) 
               {
                   Transport transport = null;
                   MimeMessage message = null;
                   SmtpRelay mainXrelay = new SmtpRelay(
                    "email-smtp.us-east-1.amazonaws.com", 587
                    );

                    message = mainXrelay.createNewEmail("h.westmoreland.cloud@mail.com", 
                    "hwestmoreland@protonmail.com", "hwestmoreland@protonmail.com, h.westmoreland.cloud@mail.com", 
                    "It's a hard Stop", 
                    gFile.toString()
                    );
         
                    mainXrelay.issueNotification( transport, message );  
               }
               catch( Exception e )
               {
                   System.out.println(" E ");
               }
            }   
       });

       try{
           context.start();
            Thread.sleep(3000);
           context.stop();
       }
       catch( Exception e )
       {
                   Transport transport = null;
                   MimeMessage message = null;
                   SmtpRelay mainXrelay = new SmtpRelay(
                    "email-smtp.us-east-1.amazonaws.com", 587
                    );

                    message = mainXrelay.createNewEmail("h.westmoreland.cloud@mail.com", 
                    "hwestmoreland@protonmail.com", "hwestmoreland@protonmail.com, h.westmoreland.cloud@mail.com", 
                    "It's a hard Stop", 
                    e.toString()
                    );
         
                    mainXrelay.issueNotification( transport, message );  
       }
      
       return;
   }

};