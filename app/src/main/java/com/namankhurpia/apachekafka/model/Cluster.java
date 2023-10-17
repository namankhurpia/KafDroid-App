package com.namankhurpia.apachekafka.model;

public class Cluster {

    public int Id;
    public String ClusterName;
    public String BootstrapServer;
    public String ZookeeperServer;
    public String Color;

    public Cluster() {
    }

    public Cluster(int id, String clusterName, String bootstrapServer, String zookeeperServer, String color) {
        Id = id;
        ClusterName = clusterName;
        BootstrapServer = bootstrapServer;
        ZookeeperServer = zookeeperServer;
        Color = color;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getClusterName() {
        return ClusterName;
    }

    public void setClusterName(String clusterName) {
        ClusterName = clusterName;
    }

    public String getBootstrapServer() {
        return BootstrapServer;
    }

    public void setBootstrapServer(String bootstrapServer) {
        BootstrapServer = bootstrapServer;
    }

    public String getZookeeperServer() {
        return ZookeeperServer;
    }

    public void setZookeeperServer(String zookeeperServer) {
        ZookeeperServer = zookeeperServer;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }
}
