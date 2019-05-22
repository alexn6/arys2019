package com.example.labarys2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeadsRepository {

    static LeadsRepository repository = new LeadsRepository();
    private HashMap<String, Lead> leads = new HashMap<>();

    public static LeadsRepository getInstance() {
        return repository;
    }

    private LeadsRepository() {
        saveLead(new Lead("Alexander Pierrot", "CEO", "Insures S.O.", R.drawable.burbuja));
        saveLead(new Lead("Carlos Lopez", "Asistente", "Hospital Blue", R.drawable.burbuja));
        saveLead(new Lead("Sara Bonz", "Directora de Marketing", "Electrical Parts ltd", R.drawable.burbuja));
        saveLead(new Lead("Liliana Clarence", "Diseñadora de Producto", "Creativa App", R.drawable.burbuja));
        saveLead(new Lead("Benito Peralta", "Supervisor de Ventas", "Neumáticos Press", R.drawable.burbuja));
        saveLead(new Lead("Juan Jaramillo", "CEO", "Banco Nacional", R.drawable.burbuja));
        saveLead(new Lead("Christian Steps", "CTO", "Cooperativa Verde", R.drawable.burbuja));
        saveLead(new Lead("Alexa Giraldo", "Lead Programmer", "Frutisofy", R.drawable.burbuja));
        saveLead(new Lead("Linda Murillo", "Directora de Marketing", "Seguros Boliver", R.drawable.burbuja));
        saveLead(new Lead("Lizeth Astrada", "CEO", "Concesionario Motolox", R.drawable.burbuja));
    }

    private void saveLead(Lead lead) {
        leads.put(lead.getId(), lead);
    }

    public List<Lead> getLeads() {
        return new ArrayList<>(leads.values());
    }

}

