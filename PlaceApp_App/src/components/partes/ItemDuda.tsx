import React from 'react';

interface ItemDudaProps {
    numero: string;
    titulo: string;
    texto: string;
}

const ItemDuda: React.FC<ItemDudaProps> = ({ numero, titulo, texto }) => {
    return (
        <article className='item-duda'>
            <h3 className='titulo-duda'><span className='num-duda'>{numero}</span>{titulo}</h3>
            <p>{texto}</p>
        </article>
    );
}

export default ItemDuda;