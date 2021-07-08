package pacote.code.servicos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;

import pacote.code.Horario.AgendaDeHorarios;
import pacote.code.entidades.Cliente;
import pacote.code.entidades.Funcionario;
import pacote.controllers.MainFrameController;

public class SistemaBarbeariaCL implements SistemaBarbearia{


    public static HashMap<String, Funcionario> funcionarios;
    public static HashMap<String, Cliente> clientes;
    public static AgendaDeHorarios agendaDeHorarios = new AgendaDeHorarios();

    
    
    private File FILE_CLIENTES = new File("clientes.txt");
    //private File FILE_PRODUTOS = new File("produtos.txt");
    private File FILE_FUNCIONARIOS = new File("funcionarios.txt");
    private File FILE_HORARIOS = new File("horarios.txt");


    public SistemaBarbeariaCL() {
        this.clientes = new HashMap<String, Cliente>();
        this.funcionarios = new HashMap<String, Funcionario>();
    }


    @Override
    public HashMap<String, Cliente> pesquisarTodosClientes(){
        return this.clientes;

    }
    public HashMap<String, Cliente> getClientes(){
        return this.clientes;
    }
    public HashMap<String, Funcionario> getFuncionarios(){
        return this.funcionarios;
    }

   
    @Override
    public boolean cadastrarFuncionario (String nome, String dataDeNascimento, String telefone, String email, String cpf) {

        if (this.funcionarios.size()==0) {
            
            try {
                Long.parseLong(telefone);
                Long.parseLong(cpf);
                
            } catch (Exception e) {
                return false;
            }

            try {
                
                Long.parseLong(nome);
                return false;
            } catch (NumberFormatException e) {
                Funcionario f = new Funcionario(nome, dataDeNascimento, telefone, email, cpf);
                this.funcionarios.put(cpf, f);
                return true;
            }

        } else {

            if (this.existeFuncionario(cpf)) {
                return false;
            } else {

                try {
                    Long.parseLong(telefone);
                    Long.parseLong(cpf);
                    
                } catch (Exception e) {
                    return false;
                }
    
                try {
                    
                    Long.parseLong(nome);
                    return false;
                } catch (NumberFormatException e) {
                    Funcionario f = new Funcionario(nome, dataDeNascimento, telefone, email, cpf);
                    this.funcionarios.put(cpf, f);
                    return true;
                }

            }

        }
    }

    @Override
    public boolean existeFuncionario(String cpf) {
        if (this.funcionarios.containsKey(cpf)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Collection<Funcionario> pesquisaFuncionarioPorNome(String nome) {
        Collection<Funcionario> funcionariosPorNome = new ArrayList<>();
        for (Funcionario f: this.funcionarios.values()) {
            if (f.getNome().toUpperCase().startsWith(nome.toUpperCase())) {
                funcionariosPorNome.add(f);
            }
        }
        return funcionariosPorNome;
    }

    @Override
    public boolean cadastrarCliente(String nome, String telefone, String email) {

        if (this.clientes.size()==0) {
            
            try {
                Long.parseLong(telefone);
                
            } catch (Exception e) {
                return false;
            }

            try {
                
                Long.parseLong(nome);
                return false;
            } catch (NumberFormatException e) {
                Cliente c = new Cliente(nome, telefone, email);
                this.clientes.put(telefone, c);
                return true;
            }
        } else {

            if(this.existeCliente(telefone)){
                return false;
            }

            try {
                Long.parseLong(telefone);
                
            } catch (Exception e) {
                return false;
            }

            try {
                
                Long.parseLong(nome);
                return false;
            } catch (NumberFormatException e) {
                Cliente c = new Cliente(nome, telefone, email);
                this.clientes.put(telefone, c);
                return true;
            }

        }
    }

    @Override
    public boolean existeCliente(String telefone) {

        if(this.clientes.containsKey(telefone)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Cliente pesquisarCliente(String telefone) {

        for (String i : this.clientes.keySet()) {
            if (this.clientes.get(i).getTelefone().equals(telefone)) {
                return this.clientes.get(i);

            }
        }
        return null;

    }
    @Override
    public boolean gravarDados(){

        try {
            FileWriter fileWriterC = new FileWriter(FILE_CLIENTES);
            BufferedWriter bwC = new BufferedWriter(fileWriterC);
            for (Cliente cliente : this.clientes.values()) {
                String stringClienteComposta = cliente.getNome() +"#"+ cliente.getTelefone() +"#"+ cliente.getEmail();
                bwC.write(stringClienteComposta);
                bwC.newLine();
            }
            bwC.flush();
            fileWriterC.close();

            FileWriter fileWriterH = new FileWriter(FILE_HORARIOS);
            BufferedWriter bwH = new BufferedWriter(fileWriterH);
            for (String k : this.agendaDeHorarios.getHorariosAgendados().keySet()) {
                String stringHorarioComposto = this.agendaDeHorarios.getHorariosAgendados().get(k).get(Calendar.DAY_OF_MONTH) +"#"+ agendaDeHorarios.getHorariosAgendados().get(k).get(Calendar.MONTH)+"#"+ this.agendaDeHorarios.getHorariosAgendados().get(k).get(Calendar.YEAR) + "#" + this.agendaDeHorarios.getHorariosAgendados().get(k).get(Calendar.HOUR) +"#"+ this.agendaDeHorarios.getHorariosAgendados().get(k).get(Calendar.MINUTE) +"#"+k;
                bwH.write(stringHorarioComposto);
                bwH.newLine();
            }
            bwH.flush();
            fileWriterH.close();

            /*
            FileWriter fileWriterP = new FileWriter(FILE_PRODUTOS);
            BufferedWriter bwP = new BufferedWriter(fileWriterP);
            
            String stringProdutoComposto;
            for (Produto produto : produtos.values()) {
                //stringProdutoComposto = produto.getNome() + "#" + produto.getPreco();
                stringProdutoComposto = produto.getNome() +"#"+ produto.getPreco() +"#"+ produto.getCodigo() +"#"+ produto.getCategoriaVenda() +"#"+ produto.getQuantidadeProdutoEstoque() +"#"+ produto.getDescricao();
                
                bwP.write(stringProdutoComposto);
                bwP.newLine();
            }

            bwP.flush();
            fileWriterP.close();
            */
            

            FileWriter fileWriterF = new FileWriter(FILE_FUNCIONARIOS);
            BufferedWriter bwF = new BufferedWriter(fileWriterF);
            for (Funcionario funcionario : this.funcionarios.values()) {
                String stringFuncionarioComposta = funcionario.getNome() +"#"+ funcionario.getDataDeNascimento() +"#"+ funcionario.getTelefone() +"#"+ funcionario.getEmail() + "#" + funcionario.getCpf();
                bwF.write(stringFuncionarioComposta);
                bwF.newLine();
            }
            bwF.flush();
            fileWriterF.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            return false;
        }
    }

    @Override
    public void recuperarDados(){

        try{
            FileReader fileReaderC = new FileReader(FILE_CLIENTES);
            BufferedReader brC = new BufferedReader(fileReaderC);
            String lineC;
            while((lineC = brC.readLine()) != null){
                String nome, telefone, email;
                String vetor [] = lineC.split("#");
                nome = vetor[0];
                telefone = vetor[1];
                email = vetor[2];
                Cliente c = new Cliente(nome, telefone, email); 
                this.clientes.put(c.getTelefone(), c );        
            }
            FileReader fileReaderH = new FileReader(FILE_HORARIOS);
            BufferedReader brH = new BufferedReader(fileReaderH);
            String lineH;
            while((lineH = brH.readLine()) != null){
                int day, month, year, hour, minute;
                String vetor [] = lineH.split("#");
                day = Integer.parseInt(vetor[0]);
                month = Integer.parseInt(vetor[1]);
                year = Integer.parseInt(vetor[2]);
                hour = Integer.parseInt(vetor[3]);
                minute = Integer.parseInt(vetor[4]);
                String key = vetor[5];
                Calendar c = Calendar.getInstance(); 
                c.set(Calendar.DAY_OF_MONTH, day);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.YEAR, year);
                c.set(Calendar.HOUR, hour);
                c.set(Calendar.MINUTE, minute);
                this.agendaDeHorarios.addRetornoDoArquivo(c, key);      
            }
            /*
            FileReader fileReaderP = new FileReader(FILE_PRODUTOS);
            BufferedReader brP = new BufferedReader(fileReaderP);
            String lineP;
            while((lineP = brP.readLine()) != null){
                String nome;
                Double preco;
                String codigo;
                String categoriaVenda;
                int quantidadeProdutosEstoque;
                String descricao;
                
                String vetor [] = lineP.split("#");
                nome = vetor[0];
                preco = Double.parseDouble(vetor[1]);
                codigo = vetor[2];
                categoriaVenda = vetor[3];
                quantidadeProdutosEstoque = Integer.parseInt(vetor[4]);
                descricao = vetor[5];
                ProdutoFisico p = new ProdutoFisico(nome, preco, codigo, categoriaVenda, quantidadeProdutosEstoque, descricao);
                this.produtos.put(p.getCodigo(), p);
                
            }
            */

            FileReader fileReaderF = new FileReader(FILE_FUNCIONARIOS);
            BufferedReader brF = new BufferedReader(fileReaderF);
            String lineF;
            while((lineF = brF.readLine()) != null){
                String nome, dataDeNascimento, telefone, email, cpf;
                String vetor [] = lineF.split("#");
                nome = vetor[0];
                dataDeNascimento = vetor[1];
                telefone = vetor[2];
                email = vetor[3];
                cpf = vetor[4];
                Funcionario f = new Funcionario(nome, dataDeNascimento, telefone, email, cpf);
                this.funcionarios.put(f.getCpf(), f);        
            }  

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}