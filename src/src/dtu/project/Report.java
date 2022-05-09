package dtu.project;

public class Report {
    public Project project;
    String name;
    int startWeek;
    int endWeek;
    int budget;
    Developer projectManager;

    public Report(Project project) {
        this.project = project;

    }

    public void printReport() {
        System.out.println("Budget used: " + project.getBudgetUsed() +
                "Budget estimate: " + project.getEstimatedBudget() +
                "Time used:" + project.getUsedTime() +
                "Time estimate: " + project.getEstimatedTime()
        );
    }


}
