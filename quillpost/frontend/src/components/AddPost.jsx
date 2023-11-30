import React, { useEffect, useRef, useState } from 'react'
import { Button, Card, CardBody, Container, Form, Input, Label } from 'reactstrap'
import { loadAllCategories } from '../services/category-service'

import JoditEditor from 'jodit-react';
import { createPost as doCreatePost } from '../services/post-service';
import { getCurrentUserDetail } from '../auth';
import { toast } from 'react-toastify';

const AddPost = () => {

    const editor = useRef(null)
    //const [content, setContent] = useState('')
    const [categories, setCategories] = useState([])
    const [user, setUser] = useState(undefined)


    const [post, setPost] = useState({
        title: '',
        content: '',
        categoryID: ''
    })

    //console.log(JSON.parse(localStorage.getItem('data')))

    useEffect(
        () => {

            setUser(getCurrentUserDetail())
            loadAllCategories().then((data) => {
                console.log(data)
                setCategories(data)
            }).catch(error => {
                console.log(error)
            })
        },
        []
    )

    //field change function
    const fieldChanged = (event) => {
        //console.log(event)
        setPost({...post, [event.target.name]: event.target.value})
    }

    const contentFieldChanged = (data) => {
        setPost({...post, 'content': data})
    }

    //create post function
    const createPost = (event) => {
        event.preventDefault();
        console.log(post)
        if(post.title.trim() === ''){
            toast.error("post title is required !!")
            return;
        }

        if(post.content.trim() === ''){
            toast.error("post content is required !!")
            return;
        }

        if(post.categoryID === ''){
            toast.error("select category ID !!")
            return;
        }

        //submit the form on server
        //post['username'] = user.id
        doCreatePost(post).then(data => {
            toast.success("Post Created!!")
            //console.log(post)
            setPost({
                title: '',
                content: '',
                categoryID: ''
            })
        }).catch((error) => {
            toast.error("Post not created due to some error!!")
            console.log(error)
        }
    )}


    return (
        <div className="wrappper">


            <Card className="shadow-sm border-0 mt-5" >

                <CardBody>

                    <h3>What going in your mind?</h3>

                        <Form onSubmit={createPost}>

                            <div className='my-3'>
                                <Label for="title" >Post Title</Label>
                                <Input 
                                    type="text" 
                                    id="title" 
                                    placeholder="Enter Your Post Title" 
                                    className="rounded-0"
                                    name = "title"
                                    onChange={fieldChanged} 
                                />
                            </div>


                            <div className='my-3'>
                                <Label for="content" >Post Content</Label>
                                    {/* <Input 
                                        type="textarea" 
                                        id="content" 
                                        placeholder="Enter Your Post Content" 
                                        className="rounded-0"
                                        style={{height: '300px'}}
                                    /> */}

                                    <JoditEditor
                                        ref = {editor}
                                        value = {post.content}
                                        onChange={contentFieldChanged}
                                    />
                            </div>


                            <div className='my-3'>
                                <Label for="category" >Post Category</Label>
                                <Input 
                                    type="select" 
                                    id="category" 
                                    placeholder="Enter Your Post category" 
                                    className="rounded-0"
                                    name="categoryID"
                                    onChange={fieldChanged} 
                                    defaultValue={0}
                                >

                                    <option disabled value={0}>--select category--</option>

                                    {
                                        categories.map((category) => (
                                            <option value={category.categoryID} key = {category.categoryID}>
                                                {category.categoryName}
                                            </option>
                                        ))
                                    }
                                </Input>

                            </div>

                            <Container className="text-center">
                                <Button type="submit" className="rounded-0" color="primary">Create Post</Button>
                                <Button className="rounded-0 ms-3" color="danger">Reset Content</Button>
                            </Container>


                        </Form>
 
                </CardBody>

            </Card>


        </div>
    )
}
export default AddPost