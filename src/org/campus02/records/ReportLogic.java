package org.campus02.records;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportLogic implements Runnable{
    private Socket socket;

    public ReportLogic(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            ArrayList<DataRecord> list = null;
            DataRecordAnalyzer analyzer = null;
            String line;
            while ((line = br.readLine()) != null) {
                String[] cmd = line.split(" ");
                if (line.equals("exit")) socket.close();
                else if (line.equals("GetStats")) {
                    if (analyzer != null) {
                        HashMap<Integer, Double> map = analyzer.totalValuesPerYear();
                    for (Integer year : map.keySet()) {
                        bw.write(year + ": " + map.get(year));
                        bw.newLine();
                    }
                    bw.write("---END---");
                    } else bw.write("data not loaded yet");
                }
                else if (cmd.length == 2) {
                    switch (cmd[0]) {
                        case "OpenFile":
                            try {
                                list = DataRecordLoader.load(cmd[1]);
                                analyzer = new DataRecordAnalyzer(list);
                                bw.write("DataRecord data loaded with " + list.size() + " entries");
                            } catch (DataFileException e) {
                                bw.write("error while loading");
                            }
                            break;
                        case "GetSector":
                            if (list != null) {
                                bw.write(analyzer.getSector(cmd[1]).toString());
                            } else bw.write("data not loaded yet");
                            break;
                        default: bw.write("invalid command");
                    }
                }
                else bw.write("unknown command");
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
