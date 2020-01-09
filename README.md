# network-connectivity-monitor

collects DNS + PING data and graph the results

    $ gradle clean jar
    :clean
    :compileJava
    :processResources NO-SOURCE
    :classes
    :jar
    
    BUILD SUCCESSFUL
    
    Total time: 1.036 secs

Run in superuser mode with: 

    $ sudo /usr/local/apps/jdk/bin/java -jar build/libs/network-connectivity-monitor.jar 
    66,19
    21,21
    22,16

Redirect to a file to save the data:

    sudo /usr/local/apps/vds/jdk/bin/java -jar build/libs/network-connectivity-monitor.jar data.csv
