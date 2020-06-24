package Model;

public enum Nota {
    DO (48, 60) {
        @Override
        public String toString() {
            return "Do";
        }
    },
    DOMOD (49, 61) {
        @Override
        public String toString() {
            return "Do#";
        }
    },
    RE (50, 62){
        @Override
        public String toString() {
            return "Re";
        }
    },
    REMOD (51, 63){
        @Override
        public String toString() {
            return "Re#";
        }
    },
    MI (52, 64){
        @Override
        public String toString() {
            return "Mi";
        }
    },
    FA (53, 65){
        @Override
        public String toString() {
            return "Fa";
        }
    },
    FAMOD (54, 66){
        @Override
        public String toString() {
            return "Fa#";
        }
    },
    SOL (55, 67){
        @Override
        public String toString() {
            return "Sol";
        }
    },
    SOLMOD (56, 68){
        @Override
        public String toString() {
            return "Sol#";
        }
    },
    LA (57, 69){
        @Override
        public String toString() {
            return "La";
        }
    },
    LAMOD (58, 70){
        @Override
        public String toString() {
            return "La#";
        }
    },
    SI (59, 71){
        @Override
        public String toString() {
            return "Si";
        }
    };
    private int valorEscala0;
    private int valorEscala1;

    Nota (int valor1, int valor2){
        valorEscala0 = valor1;
        valorEscala1 = valor2;
    }

    public int getValueNote (int escala){
        if (escala == 0){
            return valorEscala0;
        } else {
            return valorEscala1;
        }
    }
}
