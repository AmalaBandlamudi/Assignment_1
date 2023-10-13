import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SmartSpeakersList")

public class SmartSpeakersList extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
        

		/* Checks the Tablets type whether it is microsft or sony or nintendo */

		HashMap<String, SmartSpeakers> hm = new HashMap<String, SmartSpeakers>();
		if(CategoryName==null){
			hm.putAll(SaxParserDataStore.vas);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("AGPTek"))
		   {
			 for(Map.Entry<String,SmartSpeakers> entry : SaxParserDataStore.vas.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("AGPTek"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "AGPTek";
		   }
		   else if(CategoryName.equals("MegaBass"))
		    {
			for(Map.Entry<String,SmartSpeakers> entry : SaxParserDataStore.vas.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("MegaBass"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "MegaBass";
			}
			 else if(CategoryName.equals("TikiTune"))
		    {
			for(Map.Entry<String,SmartSpeakers> entry : SaxParserDataStore.vas.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("TikiTune"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "TikiTune";
			}
		}


		Utilities utility = new Utilities(request,pw);
		utility.printHtml("header.html");
		utility.printHtml("LeftNavBar.html");
		pw.print("<div id='container' class='w-auto'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Smart Speakers</a>");
		pw.print("</h2><div class='entry d-flex'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, SmartSpeakers> entry : hm.entrySet())
		{
			SmartSpeakers va = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+va.getName()+"</h3>");
			pw.print("<strong>$"+va.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img class='object-fit-contain' src='images/Smart Speaker/"+va.getImage()+"' alt='' width='180px' height='180px'/></li>");
			
			pw.print("<form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='vas'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form>");
			
			pw.print("</ul></div></td></tr>");
			// if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("footer.html");
		
	}
}
