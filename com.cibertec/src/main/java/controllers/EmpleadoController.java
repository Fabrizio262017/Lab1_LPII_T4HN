package controllers;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import models.Empleado;

public class EmpleadoController {
	
	public String createEmpleado(String Apellidos, String Nombres, int Edad, String Sexo, double Salario) {
		
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Empleado.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		try {
            Empleado empleado = new Empleado(Apellidos, Nombres, Edad, Sexo, Salario);
            
            session.beginTransaction();
            session.save(empleado);
            session.getTransaction().commit();
            sessionFactory.close();
            return "Empleado creado";
        } catch (Exception e) {
            e.printStackTrace();
            
        } 
		return "Error al crear el empleado";
	}
	
	
	
	public String deleteEmpleado(int idEmpleado) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Empleado.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Empleado empleado = session.get(Empleado.class, idEmpleado);
			session.delete(empleado);
			session.getTransaction().commit();
			sessionFactory.close();
			
			return "Empleado eliminado correctamente";
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "Error al eliminar empleado";
	}
	
	
	
	public String updateEmpleado(int idEmpleado,String Apellidos, String Nombres) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Empleado.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Empleado empleado = session.get(Empleado.class, idEmpleado);
			empleado.setApellidos(Apellidos);
			empleado.setNombres(Nombres);
			session.update(empleado);
			session.getTransaction().commit();
			sessionFactory.close();
			
			return "Empleado actualizado correctamente";
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "Error al actualizar empleado";
	}
	
	
	
	public Empleado readerEmpleado(int idEmpleado) {
	    SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Empleado.class).buildSessionFactory();

	    Session session = sessionFactory.openSession();
	    try {
	        session.beginTransaction();
	        Empleado empleado = session.get(Empleado.class, idEmpleado);
	        session.getTransaction().commit();
	        sessionFactory.close();

	        return empleado;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}


}
