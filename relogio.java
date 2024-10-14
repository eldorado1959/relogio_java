
import java.util.Objects;

public class Clock {
    private int hours;
    private int minutes;

    // Construtor que inicializa o relógio com horas e minutos
    public Clock(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
        normalizeTime();
    }

    // Método para normalizar o tempo, garantindo que horas e minutos fiquem em intervalos válidos
    private void normalizeTime() {
        int totalMinutes = this.hours * 60 + this.minutes;
        this.hours = ((totalMinutes % 1440) + 1440) % 1440 / 60; // Garantindo que as horas estejam entre 0 e 23
        this.minutes = ((totalMinutes % 1440) + 1440) % 1440 % 60; // Garantindo que os minutos estejam entre 0 e 59
    }

    // Adiciona minutos ao relógio
    public void addMinutes(int minutes) {
        this.minutes += minutes;
        normalizeTime();
    }

    // Subtrai minutos do relógio
    public void subtractMinutes(int minutes) {
        this.minutes -= minutes;
        normalizeTime();
    }

    // Sobrescreve o método equals para comparar dois relógios
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Clock clock = (Clock) obj;
        return hours == clock.hours && minutes == clock.minutes;
    }

    // Sobrescreve o método hashCode para garantir um hash único
    @Override
    public int hashCode() {
        return Objects.hash(hours, minutes);
    }

    // Retorna o horário no formato HH:MM
    @Override
    public String toString() {
        return String.format("%02d:%02d", hours, minutes);
    }

    // Método principal para testar o código
    public static void main(String[] args) {
        Clock clock1 = new Clock(10, 30);
        Clock clock2 = new Clock(10, 30);
        Clock clock3 = new Clock(9, 45);

        System.out.println(clock1.toString()); // "10:30"
        clock1.addMinutes(40);
        System.out.println(clock1.toString()); // "11:10"
        clock1.subtractMinutes(70);
        System.out.println(clock1.toString()); // "10:00"

        System.out.println(clock1.equals(clock2)); // false
        System.out.println(clock2.equals(clock3)); // false
        System.out.println(clock1.hashCode() == clock2.hashCode()); // false
    }
}
