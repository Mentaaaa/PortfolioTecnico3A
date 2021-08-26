package obras;

public class Obras {

    public static void main(String[] args) {
        
        BancoObras.criar_tabela("Arte", "cod_local int Primary Key", "cod_arte int", "nome_arte varchar(50)",
                "data varchar(12)", "autor varchar(50)", "tipo varchar(50)");
        
        BancoObras.criar_tabela("Museu", "cod_museu int Primary Key", "nome_museu varchar(50)", 
                "endereco varchar(50)", "telefone varchar(20)", "foreign key(cod_museu) references Arte(cod_local)");
        
        Arte a = new Arte();
        a.setCod_arte(1023);
        a.setCod_local(5812);
        a.setNome_arte("Noite Estrelada");
        a.setData("1889");
        a.setAutor("Van Gogh");
        a.setTipo("Pintura a óleo");
        BancoObras.salvarArte(a);
        
        Arte a2 = new Arte();
        a2.setCod_arte(1054);
        a2.setCod_local(4679);
        a2.setNome_arte("O Pensador");
        a2.setData("1902");
        a2.setAutor("Auguste Rodin");
        a2.setTipo("Escultura em bronze e mármore");
        BancoObras.salvarArte(a2);
        
        Arte a3 = new Arte();
        a3.setCod_arte(2940);
        a3.setCod_local(9530);
        a3.setNome_arte("O grito");
        a3.setData("1893");
        a3.setAutor("Edvard Munch");
        a3.setTipo("Tinta a óleo, têmpera e giz pastel sobre cartão");
        BancoObras.salvarArte(a3);
        
        Museu m = new Museu();
        m.setCod_museu(5812);
        m.setNome_museu("The Museum of Modern Art");
        m.setEndereco("11 W 53rd St, New York, NY 10019, Estados Unidos");
        m.setTelefone("+1 212-708-9400");
        BancoObras.salvarMuseu(m);
        
        Museu m2 = new Museu();
        m2.setCod_museu(4679);
        m2.setNome_museu("Musée Rodin");
        m2.setEndereco("77 Rue de Varenne, 75007 Paris, França");
        m2.setTelefone("+33 1 44 18 61 10");
        BancoObras.salvarMuseu(m2);
        
        Museu m3 = new Museu();
        m3.setCod_museu(9530);
        m3.setNome_museu("Nasjonalgalleriet");
        m3.setEndereco("Universitetsgata 13, 0164 Oslo, Noruega");
        m3.setTelefone("+47 21 98 20 00");
        BancoObras.salvarMuseu(m3);
        
        System.out.println("Tabela Arte");
        BancoObras.visualiza_dados("Arte", "cod_local", "cod_arte", "nome_arte", "data", "autor", "tipo");
        
        System.out.println("\nTabela Museu");
        BancoObras.visualiza_dados("Museu", "cod_museu", "nome_museu", "endereco", "telefone");
        
    }
    
}
