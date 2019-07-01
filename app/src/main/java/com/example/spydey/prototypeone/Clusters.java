package com.example.spydey.prototypeone;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Clusters {
    public double  cluster1, cluster2, cluster3;
    public double clusterList[];
    private static FirebaseDatabase realdb;
    private static DatabaseReference dbRef;
    public static Clusters meditationCluster, attentionCluster;

    public Clusters() {
        Log.i("customLog", "Clusters -> Default Constructor: executed");
        this.cluster1 = 0.0;
        this.cluster2 = 0.0;
        this.cluster3 = 0.0;
        this.clusterList = new double[] {0.0, 0.0, 0.0};
    }

    public Clusters(double cluster1, double cluster2, double cluster3) {
        Log.i("customLog", "Clusters -> Parameterised Constructor: executed");
        this.cluster1 = cluster1;
        this.cluster2 = cluster2;
        this.cluster3 = cluster3;
        this.clusterList = new double[] {cluster1, cluster2, cluster3};
    }

    public static void setAttention(double cluster1, double cluster2, double cluster3)
    {
        realdb = FirebaseDatabase.getInstance();
        dbRef = realdb.getReference("Clusters").child("attentionAvg");
        dbRef.setValue(new Clusters(cluster1, cluster2, cluster3));
    }

    public static void setMeditation(double cluster1, double cluster2, double cluster3)
    {
        realdb = FirebaseDatabase.getInstance();
        dbRef = realdb.getReference("Clusters").child("meditationAvg");
        dbRef.setValue(new Clusters(cluster1, cluster2, cluster3));
    }

    public static void loadClusters()
    {
        realdb = FirebaseDatabase.getInstance();

        //attention
        dbRef = realdb.getReference("Clusters").child("attentionAvg");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                attentionCluster = dataSnapshot.getValue(Clusters.class);

                attentionCluster.clusterList =
                        new double[] {attentionCluster.cluster1, attentionCluster.cluster2, attentionCluster.cluster3};

                Log.i("customLog", "Clusters -> loadClusters(): att: "
                        + attentionCluster.cluster1 + "\t" +attentionCluster.cluster2 + "\t" +attentionCluster.cluster3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        //meditation
        dbRef = realdb.getReference("Clusters").child("meditationAvg");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                meditationCluster = dataSnapshot.getValue(Clusters.class);

                meditationCluster.clusterList =
                        new double[] {meditationCluster.cluster1, meditationCluster.cluster2, meditationCluster.cluster3};

                Log.i("customLog", "Clusters -> loadClusters(): med: "
                        +meditationCluster.cluster1 + "\t" + meditationCluster.cluster2 + "\t" +meditationCluster.cluster3);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
