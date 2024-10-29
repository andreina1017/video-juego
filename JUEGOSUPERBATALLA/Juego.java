import java.util.Scanner; 

// Esta es la Clase base que representa un personaje genérico ya sea un villano o un héroe
class Personaje {
    protected String nombre; 
    protected int fuerza;   
    protected int velocidad; 
    protected int vida_hp;  

    // Aqui creamos un constructor que es un método especial en una clase que se utiliza para inicializar objetos.
    public Personaje(String nombre, int fuerza, int velocidad, int vida_hp) {
        this.nombre = nombre; // aca estamos asignando el valor del parámetro al atributo  del objeto que se está creando.
        this.fuerza = fuerza; 
        this.velocidad = velocidad; 
        this.vida_hp = vida_hp; 
    }

    // Método para atacar a otro personaje
    public void atacar(Personaje oponente) {
        System.out.println(this.nombre + " ataca a " + oponente.nombre); 
        oponente.recibirDanio(this.fuerza); // se llama al método recibirDanio del objeto oponente, pasándole como parámetro this.fuerza que es la fuerza del personaje actual.
    }

    // Método para recibir daño
    public void recibirDanio(int danio) {
        this.vida_hp -= danio; // Reduce la vida del personaje según el daño recibido
        if (this.vida_hp < 0) {
            this.vida_hp = 0; // Asegura que la vida no sea negativa.Si, después de recibir daño, la vida es menor que 0, se ajusta a 0.
        }
        System.out.println(this.nombre + " recibe " + danio + " de daño. Vida restante: " + this.vida_hp); 
    }

    // Método para defenderse de un ataque
    public void defender(int danio) {
        int danioReducido = danio / 2; // Aquí se calcula el daño reducido que el personaje sufrirá al defenderse.
        this.vida_hp -= danioReducido; // Se actualizan los puntos de vida del personaje, restando el daño reducido calculado en la línea anterior.
        if (this.vida_hp < 0) {
            this.vida_hp = 0; // Asegura que la vida no sea negativa
        }
        System.out.println(this.nombre + " se defiende y recibe solo " + danioReducido + " de daño. Vida restante: " + this.vida_hp); 
    }

    // Método para recuperar vida
    public void recuperar() {
        this.vida_hp += 20; // Aumenta la vida del personaje en 20 puntos
        System.out.println(this.nombre + " se recupera. Vida aumentada a: " + this.vida_hp);
    }

    // Método para mostrar las estadísticas del personaje
    public void mostrarEstadisticas() {
        System.out.println(nombre + " - Fuerza: " + fuerza + ", Velocidad: " + velocidad + ", Vida: " + vida_hp); 
    }
}

// Clase que representa a un superhéroe, hereda de Personaje
class SuperHeroe extends Personaje {
    private boolean poderesAumentados; // Indica si los poderes han sido aumentados

    // Constructor para inicializar los atributos del superhéroe
    public SuperHeroe(String nombre, int fuerza, int velocidad, int vida_hp) {
        super(nombre, fuerza, velocidad, vida_hp); // Llama al constructor de la clase base
        this.poderesAumentados = false; // Inicialmente, los poderes no están aumentados
    }

    // Método para atacar, sobrescribe el método de Personaje
    @Override
    public void atacar(Personaje oponente) {
        if (poderesAumentados) { // Si los poderes están aumentados
            System.out.println(this.nombre + " ataca con sus poderes aumentados!"); 
            oponente.recibirDanio(this.fuerza + 10); // Incremento de daño
            poderesAumentados = false; // Resetea el estado de poderes aumentados
        } else {
            super.atacar(oponente); // Llama al método de ataque de la clase base
        }
    }

    // Método para aumentar los poderes
    public void aumentarPoderes() {
        this.poderesAumentados = true; // Activa la variable de poderes aumentados
        System.out.println(this.nombre + " ha aumentado sus poderes para el próximo ataque!");
    }

    // Método para realizar un ataque especial
    public void ataqueEspecial(Personaje oponente) {
        int danioEspecial = this.fuerza * 2; // Doble daño para el ataque especial
        System.out.println(this.nombre + " usa su ataque especial contra " + oponente.nombre); 
        oponente.recibirDanio(danioEspecial); // Reduce la vida del oponente
    }
}

// Clase que representa a un villano, hereda de Personaje
class Villano extends Personaje {
    // Constructor para inicializar los atributos del villano
    public Villano(String nombre, int fuerza, int velocidad, int vida_hp) {
        super(nombre, fuerza, velocidad, vida_hp); // Llama al constructor de la clase base
    }

    // Método para atacar, sobrescribe el método de Personaje
    @Override
    public void atacar(Personaje oponente) {
        System.out.println(this.nombre + " ataca de forma regular."); 
        super.atacar(oponente); // Llama al método de ataque de la clase base
    }

    // Método para hacer trampa que duplica el daño
    public void hacerTrampa(Personaje oponente) {
        System.out.println(this.nombre + " hace trampa y duplica su ataque!"); 
        oponente.recibirDanio(this.fuerza * 2); // Aplica daño duplicado al oponente
    }
}

// Esta es la clase principal para el juego
public class Juego {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 

        // Se crean los superhéroes con sus atributos
        SuperHeroe superman = new SuperHeroe("Superman", 30, 20, 100);
        SuperHeroe wonderWoman = new SuperHeroe("Wonder Woman", 28, 25, 90);
        SuperHeroe flash = new SuperHeroe("Flash", 20, 40, 80);

        // Se crean los villanos con sus atributos
        Villano lexLuthor = new Villano("Lex Luthor", 25, 15, 80);
        Villano joker = new Villano("Joker", 22, 18, 85);
        Villano blackManta = new Villano("Black Manta", 24, 17, 90);

        System.out.println("Elige tu superhéroe:");
        System.out.println("1. Superman");
        System.out.println("2. Wonder Woman");
        System.out.println("3. Flash");

        int eleccionHeroe = scanner.nextInt(); // Captura la elección del usuario
        SuperHeroe heroeElegido; // Declaración del héroe elegido

        // Asignación del héroe basado en la elección que elegimos
        switch (eleccionHeroe) {
            case 1:
                heroeElegido = superman;
                break;
            case 2:
                heroeElegido = wonderWoman;
                break;
            case 3:
                heroeElegido = flash;
                break;
            default:
                System.out.println("Opción inválida. Se seleccionará Superman por defecto.");
                heroeElegido = superman; // Selección por defecto
                break;
        } 

        System.out.println("Elige tu villano:");
        System.out.println("1. Lex Luthor");
        System.out.println("2. Joker");
        System.out.println("3. Black Manta");

        int eleccionVillano = scanner.nextInt(); // Captura la elección del usuario
        Villano villanoElegido; // Declaración del villano elegido

        // Asignación del villano basado en la elección que hemos elegido
        switch (eleccionVillano) {
            case 1:
                villanoElegido = lexLuthor;
                break;
            case 2:
                villanoElegido = joker;
                break;
            case 3:
                villanoElegido = blackManta;
                break;
            default:
                System.out.println("Opción inválida. Se seleccionará Lex Luthor por defecto.");
                villanoElegido = lexLuthor; // Selección por defecto
                break;
        } 

        // Mostrar estadísticas iniciales
        System.out.println("\nEstadísticas iniciales:");
        heroeElegido.mostrarEstadisticas(); 
        villanoElegido.mostrarEstadisticas(); 

        boolean finBatalla = false; // Esta variable controla el final de la batalla
        while (!finBatalla) { // Este bucle se ejecuta mientras la batalla no haya terminado
            
            System.out.println("\nElige una acción para " + heroeElegido.nombre + ":");
            System.out.println("1. Atacar");
            System.out.println("2. Ataque Especial");
            System.out.println("3. Defender");
            System.out.println("4. Recuperarse");
            System.out.println("5. Aumentar Poderes");

            int eleccion = scanner.nextInt(); // Captura la elección de acción del usuario

            // Ejecutar acción del héroe basado en la elección que elegimos
            switch (eleccion) {
                case 1:
                    heroeElegido.atacar(villanoElegido); // Aquí llamamos al método de atacar
                    break;
                case 2:
                    heroeElegido.ataqueEspecial(villanoElegido); // Aquí llamamos al método de ataque especial
                    break;
                case 3:
                    System.out.println(heroeElegido.nombre + " se prepara para defenderse."); 
                    heroeElegido.defender(villanoElegido.fuerza); // Aquí llamamos al método de defensa
                    break;
                case 4:
                    heroeElegido.recuperar(); // Aquí llamamos al método de recuperación
                    break;
                case 5:
                    heroeElegido.aumentarPoderes(); // Aquí llamamos al método para aumentar poderes
                    break;
                default:
                    System.out.println("Opción inválida."); 
            }

            // Verificación de derrota del villano
            if (villanoElegido.vida_hp <= 0) {
                System.out.println(villanoElegido.nombre + " ha sido derrotado. ¡Victoria para " + heroeElegido.nombre + "!"); 
                finBatalla = true; // Se termina la batalla
                break; // Sale del bucle
            }

            // Ataque del villano
            System.out.println("\n" + villanoElegido.nombre + " ataca a " + heroeElegido.nombre);
            villanoElegido.atacar(heroeElegido); // Llama al método de ataque del villano

            // Verificación de derrota del héroe
            if (heroeElegido.vida_hp <= 0) {
                System.out.println(heroeElegido.nombre + " ha sido derrotado. ¡El villano gana!"); 
                finBatalla = true; // Se termina la batalla
                break; // Sale del bucle
            }

            // Mostrar estadísticas actuales
            System.out.println("\nEstadísticas actuales:");
            heroeElegido.mostrarEstadisticas(); 
            villanoElegido.mostrarEstadisticas(); 
        }

        scanner.close(); 
    }
}