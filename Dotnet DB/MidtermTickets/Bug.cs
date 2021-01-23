namespace MidtermTickets
{
    class Bug : Ticket
    {
        public int severity;
        public Bug(int id, string summary, int status, int priority, string submitter, string assigned, string watching, int severity) : base(id, summary, status, priority, submitter, assigned, watching) {
            this.severity = severity;
            
            this.type = "bug";
        }

        public override string ToString()
        {
            string severity = this.severity == 0 ? "LOW" : this.severity == 1 ? "MEDIUM" : this.severity == 2 ? "HIGH" : this.severity == 3 ? "FATAL" : "LOW";
            return $"{base.ToString()}\nseverity: {severity}";
        }
    }
}
