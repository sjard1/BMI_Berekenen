package nl.hu.iac.wsproducer;

import BMIMeter.Fault;
import BMIMeter.Request;
import BMIMeter.Response;
import BMIMeter.WSInterface;
import com.sun.xml.ws.developer.SchemaValidation;
import BMIMeter.*;

import javax.jws.WebService;


@WebService( endpointInterface= "BMIMeter.WSInterface")
@SchemaValidation(handler = SchemaValidationErrorHandler.class)

public class PowerServiceImpl implements WSInterface {
	@Override
	public Response calculatePower(Request request) throws Fault {
		System.out.println("Request object " + request.getX() + " " + request.getPower());
		ObjectFactory factory = new ObjectFactory();
		Response response = factory.createResponse();
		try {
			//BMI berekenen
			double result = request.getPower();
			response.setResult(result);
		} catch (Exception e) {
			PowerFault x = factory.createPowerFault();
			x.setErrorCode((short) 1);
			x.setMessage("error");
			Fault fault = new Fault(
					"FOUT FOUT FOUT", x);

			throw fault;
		}

		return response;
	}
}


//	@Override
//	public Response calculatePower(Request request) throws Fault{
//		System.out.println("Request object "+request.getX()+ " " + request.getPower());
//		ObjectFactory factory = new ObjectFactory();
//		Response response = factory.createResponse();
//		try {
//			//BMI berekenen
//			BigInteger result = request.getPower();
//			response.setResult(result);
//		} catch (Exception e) {
//			PowerFault x = factory.createPowerFault();
//			x.setErrorCode((short) 1);
//			x.setMessage("error");
//			Fault fault = new Fault(
//					"FOUT FOUT FOUT", x);
//
//			throw fault;
//		}
//
//		return response;
//	}
