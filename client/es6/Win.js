class Win extends React.Component {
    constructor(props) {
        super(props);
        this.addModule = this.addModule.bind(this);
        this.setSelected = this.setSelected.bind(this);
        this.changeModule = this.changeModule.bind(this);
        this.state = {
            modules: [
                {
                    name: "New Module",
                    definition: "",
                    uses: []
                }
            ],
            currentSelected: 0
        };
        this.state.forms = [
            e(
                Form,
                {
                    currModule: this.state.modules[0],
                    key: 0,
                    index: 0,
                    change: this.changeModule
                }
            )
        ];
        this.state.keyCounter = 1;
    }

    addModule() {
        this.setState((state, props) => {
            let newModule = {
                name: "New Module",
                definition: "",
                uses: []
            };
            return {
                modules: [...state.modules, newModule],
                forms: [...state.forms, e(
                    Form,
                    {
                        currModule: newModule,
                        key: state.keyCounter,
                        index: state.keyCounter,
                        change: this.changeModule
                    }
                )],
                keyCounter: state.keyCounter + 1
            };
        });
    }

    setSelected(index) {
        this.setState((state, props) => {
            return {
                currentSelected: index
            };
        });
    }

    changeModule(index, prop, val) {
        this.setState((state, props) => {
            let newModule = state.modules[index];
            newModule[prop] = val;
            let newModules = state.modules;
            newModules[index] = newModule;
            return {
                modules: newModules,
            };
        });
    }
    
    render() {
        return e(
            "div",
            {className: "container"},
            e(
                SideBar,
                {
                    modules: this.state.modules,
                    addModule: this.addModule,
                    select: this.setSelected,
                    currSelected: this.state.currentSelected
                }
            ),
            this.state.forms[this.state.currentSelected]
        );
    }
}
