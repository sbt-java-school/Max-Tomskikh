package ru.sbt.javaschool;
/*
@author Tomskikh Maksim
Cоздание Colletions Map и работа с ней.
В Map помещяем Фирму грузовика которые храяняться в TruckModel и грузоподъемность и id грузовика.
*/
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

enum TruckModel {Kamaz, Man, Volvo, Daf, Zil, Scania};


public class Apllication {
    private Map<Long, Truck> truckMap = new TreeMap<>();
    private Multimap<TruckModel, Truck> multimap = new MultimapList<>();

    public Apllication(TruckDao truckDao) {
        List<Truck> list = truckDao.list();
        for (Truck truck : list) {
            Truck truckPrevious = truckMap.put(truck.getId(), truck);
            if (truckPrevious != null) {
                throw new IllegalStateException("Same id");
            }
        }
    }

    public Apllication() {

    }

    public Truck TruckGetById(long TruckID) {
        Truck truck = truckMap.get(TruckID);
        if (truck == null) {
            throw new IllegalArgumentException("No Truck with this ID");
        }
        return truck;
    }

    public void TruckMapView() {
        for (Map.Entry<Long, Truck> truckEntry : truckMap.entrySet()) {
            System.out.println(truckEntry);
        }
    }

    public static void main(String[] args) {
        Apllication apllication = new Apllication();
        apllication.multimap.put(TruckModel.Kamaz, new Truck(23, 32));
        apllication.multimap.put(TruckModel.Daf, new Truck(42, 54));
        apllication.multimap.put(TruckModel.Zil, new Truck(32, 76));
        apllication.multimap.put(TruckModel.Volvo, new Truck(21,85));
        Collection <Truck> volvoTruck = apllication.multimap.get(TruckModel.Volvo);
        System.out.println(volvoTruck);


    }
}
