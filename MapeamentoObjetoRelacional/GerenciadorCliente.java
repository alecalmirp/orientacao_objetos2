import java.util.ArrayList;
import java.util.Scanner;

public class GerenciadorCliente {
    Scanner scanner;
    DaoCliente daoCliente;

    public GerenciadorCliente(){
        scanner=new Scanner(System.in);
        daoCliente=new DaoCliente();
    }

    public void menu(){
        int opcao=-1;

        while(opcao!=0){
            System.out.println("\n------------------------------------------------------------------");
            System.out.println("GERENCIAMENTO DE CLIENTES");
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
        Cliente c=new Cliente();
        System.out.println("----------------------");
        System.out.println("Cadastro de clientes");
        System.out.println("Nome");
        c.setNome(scanner.nextLine());
        System.out.println("CPF");
        c.setCpf(scanner.nextLine());
        System.out.println("RG");
        c.setRg(scanner.nextLine());
        System.out.println("Email");
        c.setEmail(scanner.nextLine());


        //passa o objeto para a camada model
        boolean inserido=daoCliente.inserir(c);
        if(inserido){
            System.out.println("Inserido com sucesso.");
        }
    }

    public void alterar(){
        System.out.println("----------------------");
        System.out.println("alteração de cliente");

        System.out.println("codigo: ");
        int codigo=Integer.parseInt(scanner.nextLine());
        Cliente c=daoCliente.buscarUm(codigo);

        if(c!=null){
            System.out.println("dados do cliente:");
            System.out.println("Codigo: "+c.getCodigo());
            System.out.println("Nome: "+c.getNome());

            String nome=scanner.nextLine();
            if(!nome.isEmpty()){
                c.setNome(nome);
            }
            System.out.println("CPF: "+c.getCpf());

            String cpf=scanner.nextLine();
            if(!cpf.isEmpty()){
                c.setCpf(cpf);
            }
            System.out.println("RG: "+c.getRg());

            String rg=scanner.nextLine();
            if(!rg.isEmpty()){
                c.setRg(rg);
            }
            System.out.println("Email: "+c.getEmail());

            String email=scanner.nextLine();
            if(!email.isEmpty()){
                c.setEmail(email);
            }

            int qtdeAlterado=daoCliente.alterar(c);
            if(qtdeAlterado>0){
                System.out.println("Atualizado com sucesso.");
            }
            
        }else{
            System.out.println("Nao encontrado.");
        }


        //passa o objeto para a camada model
        boolean inserido=daoCliente.inserir(c);
        if(inserido){
            System.out.println("Inserido com sucesso.");
        }
    }

    public void listarTodos(){
        ArrayList<Cliente> clientes=daoCliente.buscarTodos();
        System.out.println("======================");
        System.out.println("Clientes cadastrados");
        for(Cliente c:clientes){
            System.out.println(("codigo: ")+c.getCodigo()+(", nome: ")+c.getNome()+(", CPF: ")+c.getCpf()+(", rg: ")+c.getRg()+(", email: ")+c.getEmail());
        }
    }

    public void excluir(){
        System.out.println("======================");
        System.out.println("Exclusao de cliente");
        System.out.println("codigo: ");
        int codigo=Integer.parseInt(scanner.nextLine());
        int qtde=daoCliente.excluir(codigo);
        if(qtde>0){
            System.out.println("Excluido com sucesso");
        }else{
            System.out.println("Nao encontrado...");
        }
    }

    public void consultar(){
        System.out.println("======================");
        System.out.println("Consultar cliente");
        System.out.println("codigo: ");
        int codigo=Integer.parseInt(scanner.nextLine());
        Cliente c=daoCliente.buscarUm(codigo);
        if(c!=null){
            System.out.println(("codigo: ")+c.getCodigo()+(", nome: ")+c.getNome()+(", CPF: ")+c.getCpf()+(", rg: ")+c.getRg()+(", email: ")+c.getEmail());
        }else{
            System.out.println("Nao encontrado...");
        }
    }

}
