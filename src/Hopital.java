
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Hopital {
    public Map<Medecin, ListPatients> medecinPatients;

    public Hopital() {
        medecinPatients = new HashMap<>();
    }

    public void ajouterMedecin(Medecin m) {
        medecinPatients.put(m, new ListPatients());
    }

    public void ajouterPatient(Medecin m, Patient p) {
        if (medecinPatients.containsKey(m)) {
            medecinPatients.get(m).ajouterPatient(p);
        } else {
            ListPatients listPatients = new ListPatients();
            listPatients.ajouterPatient(p);
            medecinPatients.put(m, listPatients);
        }
    }

    // Avec l'api stream
    public void afficherMap() {
        medecinPatients.forEach((medecin, patients) -> {
            System.out.println("Medecin: " + medecin);
            patients.afficherPatients();
        });
    }

    // Afficher les patients d’un medecin dont le nom est "mohamed"
    public void afficherMedcinPatients(Medecin m) {
        if (medecinPatients.containsKey(m)) {
            medecinPatients.get(m).afficherPatients();
        } else {
            System.out.println("Medecin not found.");
        }
    }
    // Retourner les noms des patients dont le "numSecuriteSociale = 1"
    public List<String> RetournerNomPatients() {
        return medecinPatients.values().stream()
                .flatMap(listPatients -> listPatients.listP.stream())  // Access the listP directly
                .filter(patient -> patient.getNumSecuriteSociale() == 1)
                .map(Patient::getNom)
                .collect(Collectors.toList());
    }

}
