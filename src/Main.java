void main() {

    Scanner scan = new Scanner(System.in);

    ArrayList<Instituicao> instituicoes = new ArrayList<>();
    ArrayList<Local> locais = new ArrayList<>();
    ArrayList<Programa> programas = new ArrayList<>();
    ArrayList<Beneficiario> beneficiarios = new ArrayList<>();
    ArrayList<Profissional> profissionais = new ArrayList<>();
    ArrayList<Consulta> consultas = new ArrayList<>();
    ArrayList<Responsavel> responsaveis = new ArrayList<>();

    programas.add(new Programa("Dentista do Bem", "Atendimento para jovens carentes menores de idade"));
    programas.add(new Programa("Apolônias do Bem", "Atendimento para mulheres vitimas de violência"));

    while (true){
        System.out.println("""
                ===== Sistema de Agendamento Turma do Bem =====
                1 - Cadastrar Instituição
                2 - Cadastrar Local
                3 - Cadastrar Beneficiario
                4 - Cadastrar Profissional
                5 - Agendar Consulta
                6 - Listar Consultas
                0 - Sair
                Escolha uma opção:
                """);

        int opcao = scan.nextInt();
        scan.nextLine();

        switch (opcao) {
            case 1:
                System.out.println("Nome Instituição");
                String nome = scan.nextLine();

                System.out.println("CNPJ da Instituição: ");
                String cnpj = scan.nextLine();

                instituicoes.add(new Instituicao(nome, cnpj));

                System.out.println("Instituição Cadastrada.");
                break;
            case 2:
                if(instituicoes.isEmpty()) {
                    System.out.println("Cadastre uma instituição antes de continuar!");
                    break;
                }

                System.out.println("Nome do Local: ");
                String nomeLocal = scan.nextLine();

                System.out.println("Endereço: ");
                String endereco =  scan.nextLine();

                System.out.println("Tipo: ");
                String tipoLocal = scan.nextLine();

                System.out.println("Selecione a qual instituição esse local pertence: ");
                for(int i = 0; i < instituicoes.size(); i++) {
                    System.out.println(i + " - " + instituicoes.get(i).getNome());
                }
                int idInstituicao = Integer.parseInt(scan.nextLine());

                locais.add(new Local(instituicoes.get(idInstituicao).getCnpj(), nomeLocal, endereco, tipoLocal));
                System.out.println("Local Cadastrado.");

                break;

            case 3:
                System.out.println("Nome do Beneficiário: ");
                String nomeBen = scan.nextLine();

                System.out.println("Idade do Beneficiário: ");
                int idade = Integer.parseInt(scan.nextLine());

                System.out.println("CPF do Beneficiário: ");
                String cpfBen = scan.nextLine();

                System.out.println("Sexo(Masculino/Feminino): ");
                String sexo = scan.nextLine().toLowerCase();

                System.out.println("Selecione o programa que deseja participar: ");
                for (int i = 0; i < programas.size(); i++){
                    System.out.println(i + " - " + programas.get(i).getNome());
                }
                int idPgm = Integer.parseInt(scan.nextLine());

                if(idPgm == 0 && idade >= 18){
                    System.out.println("Somente menores de idade podem fazer parte desse programa.");
                    break;
                }

                if(idPgm == 1 && sexo.equals("masculino")){
                    System.out.println("Somente mulheres podem fazer parte desse programa.");
                    break;
                }

                if(idade < 18){
                    System.out.println("Insira os dados de um maior responsável");

                    System.out.print("Nome do Responsável: ");
                    String nomeResp = scan.nextLine();

                    System.out.print("CPF do Responsável: ");
                    String cpfResp = scan.nextLine();

                    System.out.print("Contato: ");
                    String contato = scan.nextLine();

                    responsaveis.add(new Responsavel(nomeResp, cpfResp, contato));

                    for (Responsavel responsavei : responsaveis) {
                        String checarCpf = responsavei.getCpf();

                        if (checarCpf.equals(cpfResp)) {
                            beneficiarios.add(new Beneficiario(nomeBen, idade, cpfBen, sexo, responsavei, programas.get(1)));
                            System.out.println("Beneficiário cadastrado!");
                        }
                    }
                } else {
                    System.out.print("Contato: ");
                    String contato = scan.nextLine();

                    responsaveis.add(new Responsavel(nomeBen, cpfBen, contato));

                    for (Responsavel responsavei : responsaveis) {
                        String checarCpf = responsavei.getCpf();

                        if (checarCpf.equals(cpfBen)) {
                            beneficiarios.add(new Beneficiario(nomeBen, idade, cpfBen, sexo, responsavei, programas.get(1)));
                            System.out.println("Beneficiário cadastrado!");
                        }
                    }
                }

                break;

            case 4:
                System.out.println("Nome do Profissional: ");
                String nomeProf = scan.nextLine();

                System.out.println("CPF do Profissional: ");
                String cpfProf = scan.nextLine();

                System.out.println("CRO do Profissional: ");
                String croProf = scan.nextLine();

                System.out.println("Especialização: ");
                String especializacao = scan.nextLine();

                profissionais.add(new Profissional(nomeProf, cpfProf, croProf, especializacao));
                System.out.println("Profissional Cadastrado.");
                break;

            case 5:
                if(profissionais.isEmpty() || beneficiarios.isEmpty() || locais.isEmpty()){
                    System.out.println("Cadastre: Profissional, Beneficiário e Local.");
                    break;
                }

                System.out.println("Insira o CPF do Beneficiário:");
                String cpf = scan.nextLine();

                for(int index = 0; index < beneficiarios.size(); index++){
                    if(cpf.equals(beneficiarios.get(index).getCpf())){
                        int idBenef;
                        idBenef = index;

                        String codigo = UUID.randomUUID().toString();

                        System.out.println("Escolha o profissional: ");
                        for(int i = 0; i < profissionais.size(); i++){
                            System.out.println(i + " - " + profissionais.get(i).getNome());
                        }
                        int idProf = Integer.parseInt(scan.nextLine());

                        System.out.println("Escolha o Local: ");
                        for(int i = 0; i < locais.size(); i++){
                            System.out.println(i + " - " + "Nome: " + locais.get(i).getEndereco() + "Endereço: " + locais.get(i).getEndereco());
                        }
                        int idLocal = Integer.parseInt(scan.nextLine());

                        System.out.println("Tipo da Consulta: ");
                        String tipo = scan.nextLine();

                        System.out.println("Descrição: ");
                        String desc = scan.nextLine();

                        Profissional prof = profissionais.get(idProf);
                        if(prof.getAgenda() == null){
                            prof.setAgenda(new Agenda(prof));
                        }

                        Consulta consulta = new Consulta(codigo, prof, beneficiarios.get(idBenef), locais.get(idLocal), tipo, desc);
                        consultas.add(consulta);
                        prof.getAgenda().adicionarConsulta(consulta);

                        System.out.println("Consulta cadastrada! " + "Codigo da Consulta: " + codigo);
                        break;

                    }
                }

            case 6:
                System.out.println("=== Consultas Turma do Bem ===");
                for(Consulta c : consultas){
                    System.out.println("Código: " + c.getCodigoConsulta());
                    System.out.println("Profissional: " + c.getProfissional().getNome());
                    System.out.println("Beneficiário: " + c.getBeneficiario().getNome());
                    System.out.println("Local: " + c.getLocal().getNome() + "Endereço: " + c.getLocal().getEndereco());
                    System.out.println("Tipo: " + c.getTipo());
                    System.out.println("Descrição: " + c.getDescricao());
                    System.out.println("------------------------------------------");
                }
                break;

            case 0:
                System.out.println("Encerrando Sistema...");
                scan.close();
                return;

            default:
                System.out.println("Opção Inválida!");
        }
    }
}
