package by.darchyk.banksystem.command;

public enum CommandEnum {
	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},
	LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	},
	REG {
		{
			this.command = new RegCommand();
		}

	},
	REG_CLIENT {
		{
			this.command = new AddClientCommand();
		}
	},
	DEPOSIT {
		{
			this.command = new MyDepositsCommand();
		}
	},
	HISTORY {
		{
			this.command = new HistoryCommand();
		}
	},
	NEW_DEPOSIT {
		{
			this.command = new NewDepositCommand();
		}
	},
	HOME {
		{
			this.command = new HomeCommand();
		}
	},
	ADD_DEPOSIT {
		{
			this.command = new AddDepositCommand();
		}
	},
	PLUS_DEPOSIT {
		{
			this.command = new PlusDepositCommand();
		}
	},
	ADD_SUM {
		{
			this.command = new AddSumCommand();
		}
	},
	REDUCE_DEPOSIT {
		{
			this.command = new ReduceDepositCommand();
		}
	},
	REDUCE_SUM {
		{
			this.command = new ReduceSumCommand();
		}
	},
	ADMIN_MAIN {
		{
			this.command = new AdminMainCommand();
		}
	},
	USERS {
		{
			this.command = new GetUsersCommand();
		}
	},
	SET_ADMIN
	{
		{
			this.command = new SetAdminCommand();
		}
	},
	DELETE_USER
	{
		{
			this.command = new DeleteUserCommand();
		}
	},
	SET_USER
	{
		{
			this.command = new SetUserCommand();
		}
	},
	SAVE
	{
		{
			this.command = new SaveCommand();
		}
	};

	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}
}
