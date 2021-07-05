### getting a git project set up and configured
- Open up the Command Prompt

- `cd <path_to_project_folder>`; points the Command Prompt to the folder that you're going to be working in

- `git init`; initializes an empty Git repository

- `git remote add origin https://github.com/shadeRed/Northwind-Web.git`; adds our GitHub repository to your Git project

- `git pull origin`; grabs the file structure and branch information from GitHub

- `git checkout <branch_name>`; `<branch_name>` is either `tachatz-dev` or `apells-dev`

Once all of that is said and done, you can now start development on your branch.

### pushing changes to your branch

- `git add *`; adds every file in your Git project to the Git watchlist so it can compare the new files to the old ones. This also takes into account the `.gitignore` file which specifically removes sensitive and unnecessary files such as `appsettings.json` and the `/bin` folder

- `git commit -m "<your_commit_message>"`; `<your_commit_message>` would be some sort of message pertaining to what was changed in that commit

- `git push origin <branch_name>`; pushes the changes to the remote GitHub repository under the specified branch (`<branch_name>` is either `tachatz-dev` or `apells-dev`)

These changes should then be reflected on GitHub as commits under your specific development branch. Changes pushed to the master branch can then be merged into each individual development branch on GitHub as needed. If you need to pull changes from a branch (i.e. master got merged into your dev branch and you need to pull the new version of your dev branch), you can run `git pull origin <branch_name>` where `<branch_name>` would be either `tachatz-dev` or `apells-dev`
