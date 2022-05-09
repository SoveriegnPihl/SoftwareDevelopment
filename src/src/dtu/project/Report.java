package dtu.project;

//Lavet af Marcus

public class Report {
    public Project project;

    public Report(Project project) {
        this.project = project;
    }

    public void printReport() {
        System.out.println("Budget used: " + project.getBudgetUsed() + "Budget estimate: " + project.getEstimatedBudget() + "Time used:" + project.getUsedTime() + "Time estimate: " + project.getEstimatedTime());
    }
}
