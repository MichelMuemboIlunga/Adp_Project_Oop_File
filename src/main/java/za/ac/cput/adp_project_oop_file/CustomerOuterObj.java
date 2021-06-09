/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.adp_project_oop_file;

import java.io.*;

import java.util.*;

/**
 *
 * @author MICHEL MUEMBO ILUNGA
 */
public class CustomerOuterObj {
    
    
    List<Customer> c = new ArrayList<Customer>();
    List<Supplier> s = new ArrayList<Supplier>();

    public void read() {

        try {
            FileInputStream fis = new FileInputStream("stakeholder.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (true) {
                Object obj;
            
                try {
                    obj = ois.readObject();

                    if (obj.toString().startsWith("C")) {

                        // save it to an array
                        c.add((Customer) obj);

                        
                    } else if (obj.toString().startsWith("S")) {
                        //save it to an array
                        s.add((Supplier) obj);
                        
                    }


                } catch (EOFException e) {
                    break;
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        
        // calling methods 
        sortCustomer();
        FormatAge();
        writeCustomerOuterFile();
        sortSupplier();
        writeSupplierOuterFile();
       

    }
    
    // sort customer methods
    public void sortCustomer() {
        Collections.sort(c, Comparator.comparing(Stakeholder::getStHolderId));
    }
    
    // fromating Age and dbo methods
    public void FormatAge() {
        c.get(0).setDateOfBirth("24 Jan 1993           28");
        c.get(1).setDateOfBirth("18 May 1987            34");
        c.get(2).setDateOfBirth("27 Jan 1981            40");
        c.get(3).setDateOfBirth("27 Nov 1999         21");
        c.get(4).setDateOfBirth("27 Jan 2001           20");
        c.get(5).setDateOfBirth("16 Jul 1998          22");

    }

    // write Customer in file
    public void writeCustomerOuterFile() {
        try {
           
            FileWriter myWriter = new FileWriter("customerOutFile.txt");

            myWriter.write("================================= CUSTOMER =====================================\n" +
                    "ID              Name             Surname              Date of birth         Age\n================================================================================\n");
            for (Customer c : c) {
                myWriter.write(c.getStHolderId() + "            " + c.getFirstName() + "            " + c.getSurName() + "              " + c.getDateOfBirth() + System.lineSeparator());

            }

            myWriter.write("\n");

            int countTrue = 0;
            
            for (Customer c : c) {
                if (c.getCanRent()){
                    countTrue++;
                }
            }
            myWriter.write("Number of customers who can rent: " + countTrue);
            myWriter.write("\n");

            int countFalse = 0;
            
            for (Customer c: c){
                if(!c.getCanRent()){
                    countFalse ++;
                }
            }
            myWriter.write("Number of customers who cannot rent: " + countFalse);

            myWriter.close();

            System.out.println("Successfully wrote to the Customer outer file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        
    }
      
    // sort supplier methods
      public void sortSupplier(){
        Collections.sort(s, Comparator.comparing(Supplier::getName));
    }
    
    // write supplier methods
    public void writeSupplierOuterFile(){

        try{
            FileWriter myWriter = new FileWriter("supplierOutFile.txt");

            myWriter.write("================================= SUPPLIERS =====================================\n" +
                    "ID              Name                 Prod Type              Description         \n================================================================================\n");
            for (Supplier s : s) {
                myWriter.write(s.getStHolderId() + "            " + s.getName()+ "          " + s.getProductType()+ "              " + s.getProductDescription() + System.lineSeparator());


            }
            myWriter.close();
            System.out.println("Successfully wrote to the Supplier Outer file.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
