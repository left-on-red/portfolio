namespace MidtermTickets
{
    class Task : Ticket
    {

        public string projectName;
        public string dueDate;
        public Task(int id, string summary, int status, int priority, string submitter, string assigned, string watching, string projectName, string dueDate) : base(id, summary, status, priority, submitter, assigned, watching) {
            this.projectName = projectName;
            this.dueDate = dueDate;

            this.type = "task";
        }

        public override string ToString()
        {
            return $"{base.ToString()}\nproject name: {this.projectName}\ndue date: {this.dueDate}";
        }
    }
}
