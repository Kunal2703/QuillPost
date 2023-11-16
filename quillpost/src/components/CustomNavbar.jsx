import { NavLink as ReactLink, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
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
} from 'reactstrap';

import { doLogout, getCurrentUserDetail, isLoggedIn } from '../auth';

const CustomNavbar = () => {

    const navigate = useNavigate()

    const [isOpen, setIsOpen] = useState(false)

    const [login, setLogin] = useState(false)
    const [user, setUser] = useState(undefined)

    useEffect(() => {

        setLogin(isLoggedIn())
        setUser(getCurrentUserDetail())

    }, [login])

    const logout =  () => {
        doLogout(() => {
            //logged out
            setLogin(false)
            navigate("/")
        })
    }

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
                            <NavLink tag = {ReactLink} to = "/">
                                Home
                            </NavLink>
                        </NavItem>


                        <NavItem>
                            <NavLink tag = {ReactLink} to = "/about">
                                About
                            </NavLink>
                        </NavItem>


                        <NavItem>
                            <NavLink tag = {ReactLink} to = "/services">
                                Services
                            </NavLink>
                        </NavItem>



                        <UncontrolledDropdown nav inNavbar>
                            <DropdownToggle nav caret>
                                more
                            </DropdownToggle>

                            <DropdownMenu right>
                                <DropdownItem tag={ReactLink } to = "/services">Contact Us</DropdownItem>

                                <DropdownItem divider />

                                <DropdownItem>
                                    Extra
                                </DropdownItem>

                            </DropdownMenu>
                        </UncontrolledDropdown>
                    </Nav>

                    <Nav navbar>

                        {
                            login && (
                                <>
                                    <NavItem>
                                        <NavLink tag={ReactLink } to = "/user/profile-info">
                                            Profile Info
                                        </NavLink>
                                    </NavItem>

                                    <NavItem>
                                        <NavLink tag={ReactLink } to = "/user/dashboard">
                                            Dashboard
                                        </NavLink>
                                    </NavItem>

                                    <NavItem>
                                        <NavLink onClick={logout}>
                                            Logout
                                        </NavLink>
                                    </NavItem>

                                </>
                            )
                        }

                        {
                            !login && (
                                <>
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

                                </>
                            )
                        }

                        
                    </Nav>
                    
                </Collapse>
            </Navbar>
        </div>
    );
}

export default CustomNavbar;