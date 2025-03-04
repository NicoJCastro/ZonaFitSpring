package gm.zona_fit;

import gm.zona_fit.modeloEntity.Cliente;
import gm.zona_fit.servicio.IClienteServicio;
import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

//@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {
	@Autowired
	private IClienteServicio clienteServicio;

	private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("DB_NAME", dotenv.get("DB_NAME"));
		System.setProperty("DB_HOST", dotenv.get("DB_HOST"));
		System.setProperty("DB_PORT", dotenv.get("DB_PORT"));
		System.setProperty("DB_USER", dotenv.get("DB_USER"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		logger.info("***Inicio de la aplicación***");
		SpringApplication.run(ZonaFitApplication.class, args);
		logger.info("***Fin de la aplicación***");
	}

	@Override
	public void run(String... args) throws Exception {
		zonaFitApp();
	}

	private void zonaFitApp() {
		logger.info("***Apliación en ejecución***");
		var salir = false;
		var consola = new Scanner(System.in);
		while (!salir) {
			System.out.println("1. Listar clientes");
			System.out.println("2. Buscar cliente por id");
			System.out.println("3. Registrar cliente");
			System.out.println("4. Actualizar cliente");
			System.out.println("5. Eliminar cliente");
			System.out.println("6. Salir");
			System.out.print("Opción: ");
			var opcion = consola.nextInt();
			consola.nextLine();
			switch (opcion) {
				case 1 ->{
					List<Cliente> clientes = clienteServicio.listarClientes();
					clientes.forEach(System.out::println);
				}
				case 2 ->{
					System.out.print("Ingrese el id del cliente: ");
					var idCliente = Integer.parseInt(consola.nextLine());
					Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
					if(cliente != null) {
						System.out.println(cliente);
					} else {
						System.out.println("Cliente no encontrado");
					}
				}
				case 3 ->{
					logger.info("***Agregar cliente***");
					logger.info("Nombre: ");
					var nombre = consola.nextLine();
					logger.info("Apellido: ");
					var apellido = consola.nextLine();
					logger.info("Membresia: ");
					var membresia = Integer.parseInt(consola.nextLine());
					var cliente = new Cliente();
					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setMembresia(membresia);
					clienteServicio.guardarCliente(cliente);
					logger.info("Cliente agregado: " + cliente );
				}
				case 4 ->{
					logger.info("***Actualizar cliente***");
					logger.info("Ingrese el id del cliente: ");
					var idCliente = Integer.parseInt(consola.nextLine());
					Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
					if(cliente != null) {
						logger.info("Nombre: ");
						var nombre = consola.nextLine();
						logger.info("Apellido: ");
						var apellido = consola.nextLine();
						logger.info("Membresia: ");
						var membresia = Integer.parseInt(consola.nextLine());
						cliente.setNombre(nombre);
						cliente.setApellido(apellido);
						cliente.setMembresia(membresia);
						clienteServicio.guardarCliente(cliente);
						logger.info("Cliente actualizado: " + cliente );
					} else {
						logger.info("Cliente no encontrado");
					}
				}
				case 5 ->{
					logger.info("***Eliminar cliente***");
					logger.info("Ingrese el id del cliente: ");
					var idCliente = Integer.parseInt(consola.nextLine());
					Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
					if(cliente != null) {
						clienteServicio.eliminarCliente(cliente);
						logger.info("Cliente eliminado: " + cliente );
					} else {
						logger.info("Cliente no encontrado");
					}
				}
				case 6 ->{
					salir = true;
				}
				default -> System.out.println("Opción no válida");

			}

		}


	}
}
