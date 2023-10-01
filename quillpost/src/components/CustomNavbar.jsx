import { NavLink as ReactLink } from 'react-router-dom';
import { useState } from 'react';
import {
    Collapse,
    Navbar,
    NavbarToggler,
    NavbarBrand,
    Nav,
    NavItem,
    NavLink,
    UncontrolledDropdown,
    DropdownToggle,
    DropdownMenu,
    DropdownItem,
    NavbarText,
} from 'reactstrap';

const CustomNavbar = () => {

    const [isOpen, setIsOpen] = useState(false);

    return (
        <div>
            <Navbar
                color="dark"
                dark
                light expand="md"
                fixed=""
            >
                <NavbarBrand tag = {ReactLink} to = "/">
                    QuillPost
                </NavbarBrand>

                <NavbarToggler onClick={() => setIsOpen(!isOpen)} />

                <Collapse isOpen = {isOpen} navbar>
                    <Nav
                        className="me-auto"
                        navbar
                    >
                        <NavItem>
                            <NavLink tag = {ReactLink} to = "/about">
                                About
                            </NavLink>
                        </NavItem>

                        <NavItem>
                            <NavLink tag = {ReactLink} to = "/login">
                                Login
                            </NavLink>
                        </NavItem>

                        <NavItem>
                            <NavLink tag = {ReactLink} to = "/signup">
                                Signup
                            </NavLink>
                        </NavItem>


                        <UncontrolledDropdown nav inNavbar>
                            <DropdownToggle nav caret>
                                more
                            </DropdownToggle>

                            <DropdownMenu right>
                                <DropdownItem tag={ReactLink } to = "/services">Services</DropdownItem>

                                <DropdownItem>Contact</DropdownItem>

                                <DropdownItem divider />
                                <DropdownItem>Extra</DropdownItem>
                            </DropdownMenu>

                        </UncontrolledDropdown>
                    </Nav>
                    <NavbarText>Simple Text</NavbarText>

                </Collapse>
            </Navbar>
        </div>
    );
}

export default CustomNavbar;