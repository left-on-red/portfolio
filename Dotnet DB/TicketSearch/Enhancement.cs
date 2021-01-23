namespace TicketSearch
{
    class Enhancement : Ticket
    {
        public string software;
        public string cost;
        public string reason;
        public string estimate;
        public Enhancement(int id, string summary, int status, int priority, string submitter, string assigned, string watching, string software, string cost, string reason, string estimate) : base(id, summary, status, priority, submitter, assigned, watching) {
            this.software = software;
            this.cost = cost;
            this.reason = reason;
            this.estimate = estimate;

            this.type = "enhancement";
        }

        public override string ToString()
        {
            return $"{base.ToString()}\nsoftware: {this.software}\ncost: {this.cost}\nreason: {this.reason}\nestimate: {this.estimate}";
        }
    }
}
