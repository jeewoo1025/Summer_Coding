import java.util.*;

class Solution {
    public String solution(String[] table, String[] languages, int[] preference) {
        List<Job> jobList = new ArrayList<>();

        for(int i=0; i<table.length; i++) {
            List<String> t = Arrays.asList(table[i].split(" "));
            jobList.add(new Job(t.get(0), 0));

            for(int j=0; j<languages.length; j++) {
                if(t.contains(languages[j])) {
                    jobList.get(i).total += (6-t.indexOf(languages[j]))*preference[j];
                }
            }
        }

        Collections.sort(jobList);
        return jobList.get(0).name;
    }
}

class Job implements Comparable<Job> {
    String name;
    int total;

    public Job(String name, int total) {
        this.name = name;
        this.total = total;
    }

    public int compareTo(Job job) {
        if(this.total < job.total)  // 내림차순 정렬
            return 1;
        else if(this.total == job.total) {
            return name.compareTo(job.name);  // 알파벳 순서 정렬
        }

        return -1;
    }
}