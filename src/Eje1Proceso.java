import javax.swing.*;
import java.util.ArrayList;

public class Eje1Proceso {

    ArrayList<String> listaProfesores = new ArrayList<>();
    ArrayList<ArrayList<String>> listaGeneralEstu = new ArrayList<>();
    int error;
    int decision;
    int repeticion;

    public void iniciar() {

        do {

            menu ();
            repeticion();

        } while (repeticion == 1);

    }

    public void menu () {

        String msj = " << Menu de Opciones del sistema >>\n\n";
        msj+= "1. Solo llenar Lista de Profesores\n";
        msj+= "2. Solo llenar Lista de Estudiantes\n";
        msj+= "3. Generar lista General\n";
        msj+= "4. Consultar profesores existentes en la lista con su nombre\n";
        msj+= "5. Consultar la cantidad de estudiantes asignados a un docente especifico\n";
        msj+= "6. Consultar estudiantes existentes en la lista con su nombre\n\n";

        msj+= "Ingrese el numero del proceso que desea realizar\n";

        decision=Integer.parseInt(JOptionPane.showInputDialog(msj));

        if (decision == 2 && listaProfesores.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Debe hacer el registro de los profesores primero");
            return;
        }

        if (decision>=3){

            if (listaProfesores.isEmpty() || listaGeneralEstu.isEmpty()) {

                String msj2 = "Tenga en cuenta que para hacer los procesos del 3 en adelante, debe realizar el proceso 1 y 2 \n";
                msj2 += "Porfavor realicelos";
                JOptionPane.showMessageDialog(null, msj2, "ERROR", JOptionPane.ERROR_MESSAGE);

                return;

            }
        }

        switch (decision) {

            case 1:
                llenarListasProfesores();
                break;

            case 2:
                llenarListasEstudiantes();
                break;

            case 3:
                listaGeneral();
                break;

            case 4:
                consultaProfPorNombre();
                break;

            case 5:
                cantidadEstuPorProf();
                break;

            case 6:
                consultarEstu();
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opcion no valida", "ERROR", 0);

        }

    }


    public void llenarListasProfesores() {

        System.out.println("<< Registro de profesores >>");
        int cant = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de profesores"));

        for (int i=0; i<cant; i++) {

            do {

                String nombreProf = JOptionPane.showInputDialog("Ingrese el nombre del profesor " + (i + 1));

                if (listaProfesores.contains(nombreProf)) {

                    System.out.println("El nombre del profesor ya existe");
                    error = 1;
                } else {

                    listaProfesores.add(nombreProf);
                    error = 0;

                }
            }while (error == 1);
        }

        System.out.println("Registro de profesores Exitoso");

    }

    public void llenarListasEstudiantes () {

        System.out.println("<< Registro de estudiantes >>");

        ArrayList<String> listEstudiantes= new ArrayList<>();

        for (int i = 0; i < listaProfesores.size(); i++) {

            JOptionPane.showMessageDialog(null, "Ingrese los estudiantes " + "Para el profesor " + listaProfesores.get(i));

            listEstudiantes = new ArrayList<String>();

            int cant = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de estudiantes"));
            for (int j = 0; j < cant; j++) {

                do {

                String nombreEst = JOptionPane.showInputDialog("Nombre estudiante " + (j + 1));

                    if (listEstudiantes.contains(nombreEst)) {

                        System.out.println("El nombre ya esta registrado ");
                        error=1;

                    } else {
                        listEstudiantes.add(nombreEst);
                        error = 0;
                    }
                } while (error == 1);

            }

            listaGeneralEstu.add(listEstudiantes);

        }

        System.out.println(listaGeneralEstu);
    }

    public void listaGeneral (){

        System.out.println("<< Lista de profesores y estudiantes >>");
        ArrayList<String> listaTemporal;

        for (int i=0;  i<listaGeneralEstu.size(); i++) {

            listaTemporal=listaGeneralEstu.get(i);
            System.out.print("Profesor : "+listaProfesores.get(i)+ " = [");


            for (int j=0; j<listaTemporal.size(); j++) {

                System.out.print(listaTemporal.get(j)+ " ");

                if (j<listaTemporal.size()-1){
                    System.out.print(" , ");
                }
            }
            System.out.print("]\n");
        }
        System.out.println();
    }

    public void consultaProfPorNombre () {

        System.out.println("<< Consulta Profesor >>");
        String nombre = JOptionPane.showInputDialog(" Ingrese el nombre del profesor ");

        if (listaProfesores.contains(nombre)) {

            int posicion = listaProfesores.indexOf(nombre);
            ArrayList<String> listaEstuTemporal = listaGeneralEstu.get(posicion);

            System.out.print("Profesor : "+nombre+ " = ");
            System.out.print(" [ ");

            for (int j=0; j<listaEstuTemporal.size(); j++) {

                System.out.print(listaEstuTemporal.get(j));

                if (j<listaEstuTemporal.size()-1) {

                    System.out.print( " , ");
                }
            }
            System.out.print(" ] \n ");
        } else {
            System.out.println("No se encuentra el profesor "+nombre+ "\n");
        }
    }

    public void cantidadEstuPorProf () {

        System.out.println(" << Consulta cantidad de estudiante por profesor >> \n");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del profesor a saber la cantidad de estudiantes ");

        if (listaProfesores.contains(nombre)) {
            int posicion = listaProfesores.indexOf(nombre);
            ArrayList<String> listaEstuTempo = listaGeneralEstu.get(posicion);
            System.out.println("La cantidad de estudiantes asociados al profesor "+nombre+ " es "+listaEstuTempo.size());

        } else {

            System.out.println("No se encuentra el profesor "+nombre+ "\n");
        }
    }

    public void consultarEstu () {

        System.out.println("<< Consulta de estudiante >>");
        ArrayList <String> listTemporal;

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del estudiante a consultar ");

        for (int i=0; i<listaGeneralEstu.size(); i++) {

            listTemporal=listaGeneralEstu.get(i);

            if (listTemporal.contains(nombre)) {

                for (int j=0; j<listTemporal.size(); j++) {

                    if (listTemporal.get(j).equalsIgnoreCase(nombre)) {
                        System.out.println("Encuentra el nombre "+nombre+ "Pertenece al grupo de "+ listaProfesores.get(i));
                    }
                }
            } else {

                System.out.println("No se encuentra el nombre en el grupo de "+listaProfesores.get(i));
            }
        }
    }

    public void repeticion () {

        repeticion = Integer.parseInt(JOptionPane.showInputDialog("¿Desea regresar al menu para continuar?\n 1. Si\n 2.No\n"));
    }
}
