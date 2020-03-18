class Form extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            nameValue: props.currModule.name,
            defValue: props.currModule.definition,
            usesValue: props.currModule.uses
        };
        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleDefinitionChange = this.handleDefinitionChange.bind(this);
        this.handleUsesChange = this.handleUsesChange.bind(this);
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
        let newUsesValue = e.target.value;
        this.setState({
            usesValue: newUsesValue
        });
        this.props.change(this.props.index, "uses", newUsesValue);
    }

    render() {
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
                                "Template:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            e(
                                "input",
                                {
                                    type: "checkbox"
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
                                "Interface:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            e(
                                "input",
                                {
                                    type: "checkbox"
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
                                "Generic:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            e(
                                "input",
                                {
                                    type: "checkbox"
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
                            e(
                                "input",
                                {
                                    type: "text",
                                    id: 0,
                                    value: this.state.usesValue,
                                    onChange: this.handleUsesChange
                                }
                            )
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
                                {className: "subtitle"},
                                "Syntax"
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
                                "Exported Constants:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            e(
                                "textarea",
                                {}
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
                                "Exported Types:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            e(
                                "textarea",
                                {}
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
                                "Access Programs:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            e(NewButton)
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
                                {className: "subtitle"},
                                "Semantics"
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
                                "State Variables:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            e(
                                NewButton
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
                                "State Invariants:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            e(
                                NewButton
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
                                "Assumptions:"
                            )
                        ),
                        e(
                            "td",
                            {},
                            e(
                                "textarea",
                                {}
                            )
                        )
                    )
                )
            )
        );
    }
}
