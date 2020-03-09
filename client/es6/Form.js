class Form extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            nameValue: props.currModule.name,
            defValue: props.currModule.definition,
            usesValues: []
        };
        for (let i = 0; i < props.currModule.uses.length; i++) {
            this.state.usesValues[i] = props.currModule.uses[i];
        }
        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleDefinitionChange = this.handleDefinitionChange.bind(this);
        this.handleUsesChange = this.handleUsesChange.bind(this);
        this.addUses = this.addUses.bind(this);
        this.removeUses = this.removeUses.bind(this);
    }

    handleNameChange(e) {
        this.setState({nameValue: e.target.value});
        this.props.change(this.props.index, "name", e.target.value);
    }

    handleDefinitionChange(e) {
        this.setState({defValue: e.target.value});
        this.props.change(this.props.index, "definition", e.target.value);
    }

    handleUsesChange(e) {
        let newUsesValues = [];
        for (let i = 0; i < this.state.usesValues.length; i++) {
            if (i === parseInt(e.target.id, 10)) {
                newUsesValues[i] = e.target.value;
            }
            else {
                newUsesValues[i] = this.state.usesValues[i]; 
            }
        }
        this.setState({
            usesValues: newUsesValues
        });
        this.props.change(this.props.index, "uses", newUsesValues);
    }

    addUses() {
        this.setState((state, props) => {
            let newUsesValues = [...state.usesValues, ""];
            props.change(props.index, "uses", newUsesValues);
            return {
                usesValues: newUsesValues
            };
        });
    }

    removeUses(index) {
        this.setState((state, props) => {
            let newUsesValues = [...state.usesValues.slice(0,index), ...state.usesValues.slice(index+1)];
            props.change(props.index, "uses", newUsesValues);
            return {
                usesValues: newUsesValues
            };
        });
    }

    render() {
        let extraUses = [];
        for (let i = 1; i < this.state.usesValues.length; i++) {
            extraUses[i-1] = e(
                "tr",
                {key: i},
                e(
                    "td",
                    {},
                    ""
                ),
                e(
                    "td",
                    {},
                    e(
                        "input",
                        {
                            type: "text",
                            id: i,
                            value: this.state.usesValues[i],
                            onChange: this.handleUsesChange
                        }
                    )
                ),
                e(
                    "td",
                    {},
                    e(
                        RemoveButton,
                        {
                            click: () => this.removeUses(i)
                        }
                    )
                )
            );
        }
        return e(
            "div",
            {className: "form-container"},
            e(
                "table",
                {},
                e(
                    "tbody",
                    {},
                    e(
                        "tr",
                        {},
                        e(
                            "td",
                            {},
                            e(
                                "label",
                                {className: "form-label"},
                                "Module Name:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            e(
                                "input",
                                {
                                    type: "text",
                                    value: this.state.nameValue,
                                    onChange: this.handleNameChange
                                }
                            )
                        )
                    ),
                    e(
                        "tr",
                        {},
                        e(
                            "td",
                            {},
                            e(
                                "label",
                                {className: "form-label"},
                                "Definition:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            e(
                                "input",
                                {
                                    type: "text",
                                    value: this.state.defValue,
                                    onChange: this.handleDefinitionChange
                                }
                            )
                        )
                    ),
                    e(
                        "tr",
                        {},
                        e(
                            "td",
                            {},
                            e(
                                "label",
                                {className: "form-label"},
                                "Uses:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            (this.state.usesValues.length === 0) ?
                                e(
                                    NewButton,
                                    {
                                        click: () => this.addUses()
                                    }
                                )
                            :
                                e(
                                    "input",
                                    {
                                        type: "text",
                                        id: 0,
                                        value: this.state.usesValues[0],
                                        onChange: this.handleUsesChange
                                    }
                                )
                        ),
                        (this.state.usesValues.length > 0) ?
                            e(
                                "td",
                                {},
                                e(
                                    RemoveButton,
                                    {
                                        click: () => this.removeUses(0)
                                    }
                                )
                            )
                        :
                            null
                    ),
                    extraUses,
                    e(
                        "tr",
                        {},
                        e(
                            "td",
                            {},
                            ""
                        ),
                        e(
                            "td",
                            {},
                            (this.state.usesValues.length > 0) ?
                                e(
                                    NewButton,
                                    {
                                        click: () => this.addUses()
                                    }
                                )
                            :
                                ""
                        )
                    ),
                    e(
                        "tr",
                        {},
                        e(
                            "td",
                            {colSpan: "2"},
                            e(
                                "h2",
                                {},
                                "Syntax"
                            )
                        )
                    )
                )
            )
        );
    }
}
