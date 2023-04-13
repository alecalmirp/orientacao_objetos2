import java.util.Scanner;

public class Locadora {
    public static void main(String[] args) {
        Locadora loc=new Locadora();
        loc.menuPrincipal();
    }

    public void menuPrincipal(){
        int opcao=-1;
        Scanner scanner=new Scanner(System.in);
        while(opcao!=0){
            System.out.println("\n----------------------");
            System.out.println("MENU PRINCIPAL");
            System.out.println("[1] Gerenciar Veiculos");
            System.out.println("[2] Gerenciar Clientes");
            System.out.println("[0] Sair");
            System.out.println("----------------------");

            try{
                opcao=Integer.parseInt(scanner.nextLine());
            }catch(Exception e){
                System.out.println("Erro... Informe um numero inteiro."+e.getMessage());
            }

            switch(opcao){
                case 1:
                    GerenciadorVeiculo gv=new GerenciadorVeiculo();
                    gv.menu();
                case 2:
                    //GerenciadorCliente gv=new GerenciadorCliente();
                    //gc.menu();
                case 0:
                  System.out.println("Ate logo.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        }
    }
}
