public class Externa {
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void externa() {
        System.out.println("Externa: " + nome);
    }

    public  static class Interna {
        private  String xd;

        public void interna() {
            System.out.println("Interna: " + xd);
        }

        public String getXd() {
            return xd;
        }

        public void setXd(String xd) {
            this.xd = xd;
        }
    }
}
