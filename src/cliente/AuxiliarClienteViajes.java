package cliente;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import comun.MyStreamSocket;

/**
 * Esta clase es un modulo que proporciona la logica de aplicacion
 * para el Cliente del sevicio de viajes usando sockets de tipo stream
 */

public class AuxiliarClienteViajes {

	private final MyStreamSocket mySocket; // Socket de datos para comunicarse con el servidor
	JSONParser parser;

	/**
	 * Construye un objeto auxiliar asociado a un cliente del servicio.
	 * Crea un socket para conectar con el servidor.
	 * @param	hostName	nombre de la maquina que ejecuta el servidor
	 * @param	portNum		numero de puerto asociado al servicio en el servidor
	 */
	AuxiliarClienteViajes(String hostName, String portNum)
			throws SocketException, UnknownHostException, IOException {

		// IP del servidor
		InetAddress serverHost = InetAddress.getByName(hostName);
		// Puerto asociado al servicio en el servidor
		int serverPort = Integer.parseInt(portNum);
		//Instantiates a stream-mode socket and wait for a connection.
		this.mySocket = new MyStreamSocket(serverHost, serverPort);
		/**/  System.out.println("Hecha peticion de conexion");
		parser = new JSONParser();
	} // end constructor

	/**
	 * Devuelve los viajes ofertados desde un origen dado
	 * 
	 * @param origen	origen del viaje ofertado
	 * @return array JSON de viajes desde un origen. array vacio si no hay ninguno
	 */
	public JSONArray consultaViajes(String origen) {
			// TODO
		JSONObject envio = new JSONObject();
		envio.put("Operacion","1");
		envio.put("codOrigen",origen);
		JSONArray res = new JSONArray();

		try {
			mySocket.sendMessage(envio.toJSONString());
			res = (JSONArray) parser.parse(mySocket.receiveMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res; // cambiar por el retorno correcto
	} // end consultaViajes



	/**
	 * Un pasajero reserva un viaje
	 * 
	 * @param codviaje		codigo del viaje
	 * @param codcliente	codigo del pasajero
	 * @return	objeto JSON con los datos del viaje. Vacio si no se ha podido reservar
	 */
	public JSONObject reservaViaje(String codviaje, String codcliente) {
		// TODO
		JSONObject envio = new JSONObject();
		JSONObject res = new JSONObject();

		envio.put("Operacion","2");
		envio.put("codViaje",codviaje);
		envio.put("codCli", codcliente);

		try {
			mySocket.sendMessage(envio.toJSONString());
			res = (JSONObject) parser.parse(mySocket.receiveMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res; // cambiar por el retorno correcto
	} // end reservaViaje
	
	/**
	 * Un pasajero anula una reserva
	 * 
	 * @param codviaje		codigo del viaje
	 * @param codcliente	codigo del pasajero
	 * @return	objeto JSON con los datos del viaje. Vacio si no se ha podido reservar
	 */
	public JSONObject anulaReserva(String codviaje, String codcliente) {
		// TODO
		JSONObject envio = new JSONObject();
		JSONObject res = new JSONObject();
		envio.put("Operacion","3");
		envio.put("codViaje",codviaje);
		envio.put("codCli", codcliente);

		try {
			mySocket.sendMessage(envio.toJSONString());
			res = (JSONObject) parser.parse(mySocket.receiveMessage());

		}catch (Exception e) {
			e.printStackTrace();
		}
		return res; // cambiar por el retorno correcto
	} // end anulaReserva

	/**
	 * Un cliente oferta un nuevo viaje
	 * 
	 * @param codprop	codigo del cliente que hace la oferta
	 * @param origen	origen del viaje
	 * @param destino	destino del viaje
	 * @param fecha		fecha del viaje en formato dd/mm/aaaa
	 * @param precio	precio por plaza
	 * @param numplazas	numero de plazas ofertadas
	 * @return	viaje ofertado en formatoJSON. Vacio si no se ha podido ofertar
	 */
	public JSONObject ofertaViaje(String codprop, String origen, String destino, String fecha, long precio, long numplazas) {
		// TODO
		JSONObject envio = new JSONObject();
		JSONObject res = new JSONObject();

		envio.put("Operacion","4");
		envio.put("codOrigen",origen);
		envio.put("codCli", codprop);
		envio.put("destino",destino);
		envio.put("fecha",fecha);
		envio.put("precio",precio);
		envio.put("numplazas",numplazas);

		try {
			mySocket.sendMessage(envio.toJSONString());
			res = (JSONObject) parser.parse(mySocket.receiveMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res; // cambiar por el retorno correcto
	} // end ofertaViaje

	/**
	 * Un cliente borra una oferta de viaje
	 * 
	 * @param codviaje		codigo del viaje
	 * @param codcliente	codigo del pasajero
	 * @return	objeto JSON con los datos del viaje. Vacio si no se ha podido reservar
	 */
	public JSONObject borraViaje(String codviaje, String codcliente) {
		// TODO
		JSONObject envio = new JSONObject();
		JSONObject res = new JSONObject();

		envio.put("Operacion","5");
		envio.put("codViaje",codviaje);
		envio.put("codCliente", codcliente);

		try {
			mySocket.sendMessage(envio.toJSONString());
			res = (JSONObject) parser.parse(mySocket.receiveMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return res; // cambiar por el retorno correcto
	} // end borraViaje

	/**
	 * Finaliza la conexion con el servidor
	 */
	public void cierraSesion( ) {
		// TODO
		JSONObject envio = new JSONObject();
		envio.put("Operacion","0");
		try {
			mySocket.sendMessage(envio.toJSONString());
			System.out.println(mySocket.receiveMessage());
			mySocket.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		// cambiar por el retorno correcto
	} // end done
} //end class
