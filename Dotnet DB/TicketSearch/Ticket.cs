namespace TicketSearch
{
    class Ticket
    {
        public int id;
        public string summary;
        public int status;
        public int priority;
        public string submitter;
        public string assigned;
        public string watching;

        public string type = "none";
        public Ticket(int id, string summary, int status, int priority, string submitter, string assigned, string watching) {
            this.id = id;
            this.summary = summary;
            this.status = status;
            this.priority = priority;
            this.submitter = submitter;
            this.assigned = assigned;
            this.watching = watching;
        }

        public void Close() { this.status = 1; }

        public override string ToString()
        {
            string status = this.status == 0 ? "OPEN" : "CLOSED";
            string priority = this.priority == 0 ? "LOW" : this.priority == 1 ? "MEDIUM" : this.priority == 2 ? "HIGH" : "LOW";
            return $"id: {this.id}\nsummary: {this.summary}\nstatus: {status}\npriority: {priority}\nsubmitter: {this.submitter}\nassigned: {this.assigned}\nwatching: {this.watching}";
        }
    }
}
