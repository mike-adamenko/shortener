import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class Links extends Component {

    constructor(props) {
        super(props);
        this.state = {links: [], isLoading: true};
        this.remove = this.remove.bind(this);
        this.redirect = this.redirect.bind(this);
    }

    componentDidMount() {
        this.setState({isLoading: true});

        fetch('api/links')
            .then(response => response.json())
            .then(data => this.setState({links: data, isLoading: false}));
    }

    redirect(event){
        event.preventDefault();
        window.open(`api/redirect?shortUrl=${event.currentTarget.href}`, '_blank');
        // window.location.href = `api/redirect?shortUrl=${event.currentTarget.href}`;
        // fetch(`/api/forward?shortUrl=${event.currentTarget.href}`, {
        //     method: 'GET',
        //     mode: 'no-cors'
        // })
    }

    async remove(id) {
        await fetch(`/api/link/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedLinks = [...this.state.links].filter(i => i.id !== id);
            this.setState({links: updatedLinks});
        });
    }

    render() {
        const {links, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const linkList = links.map(link => {
            return <tr key={link.id}>
                <td style={{whiteSpace: 'nowrap'}}><a href={link.url}>{link.url}</a></td>
                <td><a href={link.shortUrl} onClick={this.redirect}>{link.shortUrl}</a></td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/links/" + link.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(link.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/links/new">Add Link</Button>
                    </div>
                    <h3>Links</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">URL</th>
                            <th width="20%">Short URL</th>
                            <th width="10%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {linkList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default Links;