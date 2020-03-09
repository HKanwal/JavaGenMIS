class SideBar extends React.Component {
    render() {
        let moduleTabs = [];
        for (let i = 0; i < this.props.modules.length; i++) {
            moduleTabs[i] = e(
                ModuleTab,
                {
                    select: this.props.select,
                    index: i,
                    key: i,
                    selected: this.props.currSelected === i,
                    name: this.props.modules[i].name
                }
            );
        }
        return e(
            "div",
            {className: "side-bar"},
            moduleTabs,
            e(NewModuleButton, {
                add: this.props.addModule
            })
        );
    }
}

class ModuleTab extends React.Component {
    constructor(props) {
        super(props);
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        if (!this.props.selected) {
            this.props.select(this.props.index);
        }
    }

    render() {
        return e(
            "div",
            {
                className: "module-container" + (this.props.selected ? " selected-module" : ""),
                onClick: this.handleClick
            },
            e(ModuleName, {name: this.props.name})
        );
    }
}

class ModuleName extends React.Component {
    render() {
        return e(
            "p",
            {
                unselectable: "on",
                className: "unselectable module-name"
            },
            this.props.name
        );
    }
}

class NewModuleButton extends React.Component {
    render() {
        return e(
            "div",
            {className: "new-module-button"},
            e(NewButton, {click: () => this.props.add()})
        );
    }
}
