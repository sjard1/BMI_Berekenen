package nl.hu.iac.wsproducer;

import BMIMeter.*;
import com.sun.xml.ws.developer.SchemaValidation;

import javax.jws.WebService;


@WebService( endpointInterface= "BMIMeter.WSInterface")
@SchemaValidation(handler = SchemaValidationErrorHandler.class)

public class PowerServiceImpl implements WSInterface {
	@Override
	public Response calculateBmiMan(Request request) throws Fault {
		System.out.println("Request object " + request.getGewicht() + " " + request.getLengte());
		ObjectFactory factory = new ObjectFactory();
		Response response = factory.createResponse();
		try {
			//BMI berekenen
			double result = request.getGewicht() / (request.getLengte() *request.getLengte());
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

	@Override
	public ResponseVrouw calculateBmiVrouw(RequestVrouw bmiBerekenVrouwRequestSoap) throws Fault {
		System.out.println("Request object " + bmiBerekenVrouwRequestSoap.getGewichtInHeleKilos() + " " + bmiBerekenVrouwRequestSoap.getLengteinCm());
		ObjectFactory factory = new ObjectFactory();
		ResponseVrouw response = factory.createResponseVrouw();
		try {
			//BMI berekenen
			double lengteInCm = bmiBerekenVrouwRequestSoap.getLengteinCm().doubleValue();
			lengteInCm/=100;
			System.out.println(lengteInCm);
			double gewicht = bmiBerekenVrouwRequestSoap.getGewichtInHeleKilos().intValue();
			System.out.println(gewicht);
			double result = (gewicht/ (lengteInCm *lengteInCm)+1);
			response.setResult(result);
		} catch (Exception e) {
		    System.out.print("kapot");
			PowerFault x = factory.createPowerFault();
			x.setErrorCode((short) 1);
			x.setMessage("error");
			Fault fault = new Fault(
					"FOUT FOUT FOUT", x);

			throw fault;
		}
        System.out.println(response.getResult());

        return response;
	}
	}


//	@Override
//	public Response calculateBmiVrouw(Request request) throws Fault {
//
//		System.out.println("Request object " + request.getGewicht() + " " + request.getLengte());
//		ObjectFactory factory = new ObjectFactory();
//		Response response = factory.createResponse();
//		try {
//			//BMI berekenen
//			double result = request.getGewicht() / (request.getLengte() *request.getLengte());
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
//		return response;
//	}
//}


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
