package com;
import model.Bill;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Bills")
public class BillService
{
 Bill billObj = new Bill();
@GET
@Path("/readbill")
@Produces(MediaType.TEXT_HTML)
public String readPayment()
{
return billObj.readBill();
}

@POST
@Path("/insertbill")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertBill(@FormParam("userid") String userid,
		 @FormParam("unitUsage") String unitUsage,
		 @FormParam("month") String month,
		 @FormParam("year") String year,
		 @FormParam("amount") String amount)
		{
		 String output = billObj.insertBill(userid, unitUsage, month, year, amount);
			return output;
		}

@PUT
@Path("/updatebill")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateBill(String billData)
{
//Convert the input string to a JSON object
 JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject();
//Read the values from the JSON object
 String id = billObject.get("id").getAsString();
 String userid = billObject.get("userid").getAsString();
 String unitUsage = billObject.get("unitUsage").getAsString();
 String month = billObject.get("month").getAsString();
 String year = billObject.get("year").getAsString();
 String amount = billObject.get("amount").getAsString();
 String output = billObj.updateBill(id, userid, unitUsage, month, year, amount);
 return output;
}

@DELETE
@Path("/deletebill")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteBill(String billData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String id = doc.select("id").text();
 String output = billObj.deleteBill(id);
return output;
}


}

