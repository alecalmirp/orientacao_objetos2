import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class GerenciadorVeiculo {
    Scanner scanner;
    DaoVeiculo daoVeiculo;

    public GerenciadorVeiculo(){
        scanner=new Scanner(System.in);
        daoVeiculo=new DaoVeiculo();
    }

    public void menu(){
        int opcao=-1;

        while(opcao!=0){
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("GERENCIAMENTO DE VEICULOS");
            System.out.println("[1] Cadastrar");
            System.out.println("[2] Consultar");
            System.out.println("[3] Alterar");
            System.out.println("[4] Excluir");
            System.out.println("[5] Listar todos");
            System.out.println("[0] Voltar ao menu anterior");
            System.out.println("\n------------------------------------------------------------------");

            try{
                opcao=Integer.parseInt(scanner.nextLine());
            }catch(Exception e){
                System.out.println("Erro... Informe um numero inteiro."+e.getMessage());
            }

            switch(opcao){
                case 1:
                    this.cadastrar();
                    break;
                case 2:
                    this.consultar();
                    break;
                case 3:
                    this.alterar();
                    break; 
                case 4:
                    this.excluir();
                    break; 
                case 5: 
                    this.listarTodos();        
                    break;  
                case 0:
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        }
    }
    
    public void cadastrar(){
        Veiculo v=new Veiculo();
        System.out.println("----------------------");
        System.out.println("Cadastro de veiculos");
        System.out.println("Marca");
        v.setMarca(scanner.nextLine());
        System.out.println("Modelo");
        v.setModelo(scanner.nextLine());
        System.out.println("Chassi");
        v.setChassi(scanner.nextLine());
        System.out.println("Ano");
        v.setAno(Integer.parseInt(scanner.nextLine()));


        //passa o objeto para a camada model
        boolean inserido=daoVeiculo.inserir(v);
        if(inserido){
            System.out.println("Inserido com sucesso.");
        }
    }

    public void alterar(){
        System.out.println("----------------------");
        System.out.println("alteração de veiculo");

        System.out.println("codigo: ");
        int codigo=Integer.parseInt(scanner.nextLine());
        Veiculo v=daoVeiculo.buscarUm(codigo);

        if(v!=null){
            System.out.println("dados do veiculo:");
            System.out.println("Codigo: "+v.getCodigo());
            System.out.println("Marca: "+v.getMarca());

            String marca=scanner.nextLine();
            if(!marca.isEmpty()){
                v.setMarca(marca);
            }
            System.out.println("Modelo: "+v.getModelo());

            String modelo=scanner.nextLine();
            if(!modelo.isEmpty()){
                v.setModelo(modelo);
            }
            System.out.println("Chassi: "+v.getChassi());

            String chassi=scanner.nextLine();
            if(!chassi.isEmpty()){
                v.setChassi(chassi);
            }
            System.out.println("Ano: "+v.getAno());

            String ano=scanner.nextLine();
            if(!ano.isEmpty()){
                v.setAno(Integer.parseInt(ano));
            }

            int qtdeAlterado=daoVeiculo.alterar(v);
            if(qtdeAlterado>0){
                System.out.println("Atualizado com sucesso.");
            }
            
        }else{
            System.out.println("Nao encontrado.");
        }


        //passa o objeto para a camada model
        boolean inserido=daoVeiculo.inserir(v);
        if(inserido){
            System.out.println("Inserido com sucesso.");
        }
    }

    public void listarTodos(){
        ArrayList<Veiculo> veiculos=daoVeiculo.buscarTodos();
        System.out.println("======================");
        System.out.println("Veiculos cadastrados");
        for(Veiculo v:veiculos){
            System.out.println(("codigo: ")+v.getCodigo()+(", marca: ")+v.getMarca()+(", modelo: ")+v.getModelo()+(", chassi: ")+v.getChassi()+(", ano: ")+v.getAno());
        }
    }

    public void excluir(){
        System.out.println("======================");
        System.out.println("Exclusao de veiculo");
        System.out.println("codigo: ");
        int codigo=Integer.parseInt(scanner.nextLine());
        int qtde=daoVeiculo.excluir(codigo);
        if(qtde>0){
            System.out.println("Excluido com sucesso");
        }else{
            System.out.println("Nao encontrado...");
        }
    }

    public void consultar(){
        System.out.println("======================");
        System.out.println("Consultar veiculo");
        System.out.println("codigo: ");
        int codigo=Integer.parseInt(scanner.nextLine());
        Veiculo v=daoVeiculo.buscarUm(codigo);
        if(v!=null){
            System.out.println(("codigo: ")+v.getCodigo()+(", marca: ")+v.getMarca()+(", modelo: ")+v.getModelo()+(", chassi: ")+v.getChassi()+(", ano: ")+v.getAno());            
        }else{
            System.out.println("Nao encontrado...");
        }
    }

}
