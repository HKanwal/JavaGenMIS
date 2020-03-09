class Win extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            modules: [
                {
                    name: "New Module"
                }
            ],
            currentSelected: 0
        };
        this.state.forms = [
            e(
                Form,
                {currModule: this.state.modules[0], key: this.state.modules.length-1}
            )
        ];
        this.addModule = this.addModule.bind(this);
        this.setSelected = this.setSelected.bind(this);
    }

    addModule() {
        this.setState((state, props) => {
            let newModule = {
                name: "New Module"
            };
            return {
                modules: [...state.modules, newModule],
                forms: [...state.forms, e(
                    Form,
                    {currModule: newModule, key: state.modules.length+1}
                )]
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
